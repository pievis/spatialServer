package it.isac.db;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.PositionType;
import it.isac.db.search.NearestNSearch;
import it.isac.db.search.RangeSearch;
import it.isac.db.search.SearchCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaworks.redis.GeoArgs;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;

/**
 * This class provide access to the redis.io nosql database. [lattuce is the
 * java client that makes the bridge.] *Nodes could be configurated to expire
 * after a specified time of inactivity.
 * 
 * @author Pievis
 */
public class RedisDB implements ISpatialDataBase {

	// Logger
	static final Logger LOGGER = Logger.getLogger(RedisDB.class.getName());

	// Redis configuration
	String clientPort = "6379";
	String clientUrl = "redis://192.168.56.101:" + clientPort;
	boolean useExpiration = true;
	long expirationSeconds = 60; // 1 minute
	// Connection state (as the documentation specifies, this is thread safe)
	StatefulRedisConnection<String, String> connection;
	RedisClient client;
	RedisCommands<String, String> console;
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * Creates the Redis Database Bridge for the model of the spatial
	 * application.
	 * 
	 * @param clientUrl
	 *            must be the url of the redis.io db. For example:
	 *            "redis://192.168.56.101:6379"
	 */
	public RedisDB(String clientUrl) {
		this.clientUrl = clientUrl;
	}

	/**
	 * Creates the Redis Database Bridge for the model of the spatial
	 * application.
	 * 
	 * @param clientUrl
	 *            must be the url of the redis.io db. For example:
	 *            "redis://192.168.56.101"
	 * @param clientPort
	 */
	public RedisDB(String clientUrl, String clientPort) {
		this.clientPort = clientPort;
		this.clientUrl = clientUrl + ":" + clientPort;
	}

	/**
	 * Get the client for redis connetcion. Redis is actually single-threaded,
	 * which is how every command is guaranteed to be atomic. While one command
	 * is executing, no other command will run.
	 * 
	 * @return redis connection
	 */
	RedisCommands<String, String> getConsole() {
		// Include sanity check to always make the connection if something
		// goes wrong and we lose it
		if (console == null || !connection.isOpen()) {
			client = RedisClient.create(clientUrl);
			connection = client.connect();
			console = connection.sync();
		}
		return console;
	}

	public boolean useExpiration() {
		return useExpiration;
	}

	public void setUseExpiration(boolean useExpiration) {
		this.useExpiration = useExpiration;
	}

	public long getExpirationSeconds() {
		return expirationSeconds;
	}

	public void setExpirationSeconds(long expirationSeconds) {
		this.expirationSeconds = expirationSeconds;
	}

	//
	// DB Interface
	// ///////////////////////////////////////////////////////////////////////

	private String nodeUniqueId(String net, String id) {
		return net + "_" + id;
	}

	public Node getNode(String net, String id) {
		Node n = null;
		RedisCommands<String, String> console = getConsole();
		String jsonObj = console.get(nodeUniqueId(net, id));
		if (jsonObj != null) {
			// parse node rappresentation from json
			try {
				n = mapper.readValue(jsonObj, Node.class);
			} catch (Exception e) {
				LOGGER.log(Level.WARNING,
						"Could not parse from redis object.\n" + jsonObj, e);
			}
		}
		return n;
	}

	/**
	 * Adds or update the node to the network with expiration time if necessary.
	 * The node is also added the the set used to query the nbr.
	 * 
	 * @param net
	 * @param n
	 */
	void createRedisNode(String net, Node n) {
		String jsonObj = null;
		try {
			jsonObj = mapper.writeValueAsString(n);
		} catch (JsonProcessingException e) {
			LOGGER.log(Level.WARNING, "Error parsing the node", e);
			return;
		}
		RedisCommands<String, String> console = getConsole();
		String key = nodeUniqueId(net, n.getId());
		console.set(key, jsonObj);

		if (useExpiration) {
			console.expire(key, expirationSeconds);
		}

		// Update the node in the respective redis set
		IPosition pos = n.getState().getPosition();
		LatLonPosition llpos;
		if (pos.getPositionType().equals(PositionType.LATLON)) {
			llpos = (LatLonPosition) pos;
		} else {
			throw new UnsupportedOperationException(
					"Redis supports only LatLong position type.");
		}

		console.geoadd(net, llpos.getLon(), llpos.getLat(), n.getId());
	}

	public void updateNodeState(String net, String id, NodeState state) {
		Node n = getNode(net, id);
		if (n == null) { // node maybe expired
			// LOGGER.log(Level.INFO, "No node to update. Adding a new node");
			n = new Node(id, state);
		} else {
			n.setState(state); // Overrides old state
		}
		createRedisNode(net, n);
	}

	public List<Node> getNeighbourhood(String net, IPosition position,
			SearchCriteria searchCriteria) {

		if (!position.getPositionType().equals(PositionType.LATLON))
			throw new UnsupportedOperationException(
					"Redis can't use position types different form LatLon");

		LatLonPosition pos = (LatLonPosition) position;

		if (searchCriteria instanceof RangeSearch) {
			return rangeSearch(net, pos, (RangeSearch) searchCriteria);
		}
		if (searchCriteria instanceof NearestNSearch) {
			// TODO implement other type of search
			throw new UnsupportedOperationException();
		}

		return null;
	}

	List<Node> rangeSearch(String net, LatLonPosition position, RangeSearch rs) {

		RedisCommands<String, String> console = getConsole();

		double distance = rs.getMeters();
		LOGGER.fine("Starting geoquery\n\tpoint: " + position.toString()
				+ "\n\trange: " + distance + " m");
		// get the ids of the nodes in the range
		Set<String> points = console.georadius(net, position.getLon(),
				position.getLat(), distance, GeoArgs.Unit.m);

		Collection<Node> ns = getNodesFromIds(new ArrayList<String>(points),
				net);
		LOGGER.fine("Gotten " + points.size() + " points\nRemoved: "
				+ (points.size() - ns.size()));
		List<Node> nodes = new ArrayList<Node>(ns);
		return nodes;
	}

	public boolean removeNode(String net, String id) {
		RedisCommands<String, String> console = getConsole();
		console.del(new String[] { nodeUniqueId(net, id) });
		console.zrem(net, new String[] { id }); // remove also from the geoset
		return true;
	}

	/**
	 * Get the nodes for the given ids in the list. Note: To maintain
	 * consistency this function provide an useful side effect, for every node
	 * representation not found in the database, its id is removed from the
	 * respective sorted geolist. (This is necessary due to expiration nodes).
	 * 
	 * @param ids
	 * @param net
	 * @return
	 */
	Collection<Node> getNodesFromIds(List<String> ids, String net) {
		Collection<Node> nodes = new ArrayList<Node>();
		for (String id : ids) {
			String ukey = nodeUniqueId(net, id);
			String json = console.get(ukey);
			if (json == null) {
				// node might be expired
				long removed = console.zrem(net, new String[] { id });
				if (removed <= 0)
					LOGGER.warning("No node removed for id " + id + " and net "
							+ net);
			} else {
				try {
					Node n = mapper.readValue(json, Node.class);
					nodes.add(n);
				} catch (Exception e) {
					LOGGER.log(Level.WARNING,
							"Could not parse from redis object.\n" + json, e);
				}
			}
		}
		return nodes;
	}

	public Collection<Node> getAllNodes(String net) {
		// get all ids in the net
		List<String> ids = getConsole().zrange(net, 0, -1);
		Collection<Node> nodes = getNodesFromIds(ids, net);
		return nodes;
	}

}
