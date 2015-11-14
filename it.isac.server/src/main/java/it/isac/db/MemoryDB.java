package it.isac.db;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.PositionType;
import it.isac.commons.model.XYPosition;
import it.isac.db.search.NearestNSearch;
import it.isac.db.search.RangeSearch;
import it.isac.db.search.SearchCriteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Simple DB that stores the information in memory.
 * *Note: Nodes don't expire using this implementation, this is for testing mostly.
 * @author Pievis
 */
public class MemoryDB implements ISpatialDataBase {

	final static Logger LOGGER = Logger.getLogger(MemoryDB.class.getName());
	
	HashMap<String, HashMap<String, Node>> nets;
	//HashMap<String, Node> nodes;
	
	public MemoryDB(){
		nets = new HashMap<String, HashMap<String, Node>>();
		//nodes = new HashMap<String, Node>();
		//Add a node for testing
		//addNode0();
	}
	
	public synchronized Node getNode(String net, String id) {
		HashMap<String, Node> nodes = getNodes(net);
		return nodes.get(id);
	}

	public synchronized void updateNodeState(String net, String id, NodeState state) {
		HashMap<String, Node> nodes = getNodes(net);
		nodes.put(id, new Node(id, state));
	}

	public List<Node> getNeighbourhood(String net, IPosition position, SearchCriteria searchCriteria) {
		
		if(searchCriteria instanceof RangeSearch){
			return rangeSearch(net, position, (RangeSearch) searchCriteria);
		}
		if(searchCriteria instanceof NearestNSearch){
			throw new UnsupportedOperationException();
		}
		return null;
	}

	public synchronized boolean removeNode(String net, String id) {
		HashMap<String, Node> nodes = getNodes(net);
		nodes.remove(id);
		return true;
	}

	public synchronized Collection<Node> getAllNodes(String net) {
		HashMap<String, Node> nodes = getNodes(net);
		return nodes.values();
	}
	
	//Search Methods
	List<Node> rangeSearch(String net, IPosition position, RangeSearch rs){
		HashMap<String, Node> nodes;
		synchronized (this) {
			nodes = getNodes(net); //synch so should be ok
		}
		ArrayList<Node> nbr = new ArrayList<Node>();
		double meters = rs.getMeters();
		String positionType = PositionType.LATLON;
		if(position instanceof XYPosition)
			positionType = PositionType.XY;
		for(Node n : nodes.values()){
			IPosition np = n.getState().getPosition();
			String npt = np.getPositionType();
			if(npt.equals(positionType)){ //Coordinates should be of the same type
				if(npt.equals(PositionType.LATLON)){
					//Distance between geo coordinates
					double distance = LatLonPosition.distance((LatLonPosition) position,
							(LatLonPosition) np, rs.getUnit());
					if(distance < rs.getRange()){
						//log("distance: " + distance + " r:" + rs.getRange());
						nbr.add(n);
					}
				}
				if(npt.equals(PositionType.XY)){
					//Distance between xy coordinates
					double distance = XYPosition.distance((XYPosition) position,
							(XYPosition) np);
					if(distance < meters){
						//log(n.getId()+"\ndistance: " + distance + " meters:" + meters);
						nbr.add(n);
					}
				}
			}
			else{
				log("Ignored node " + n.getId() + " during range search");
			}
		}
		return nbr;
	}
	
	
	/**
	 * Get the nodes of the specified network
	 * @param netId
	 * @return 
	 */
	HashMap<String, Node> getNodes(String netId){
		HashMap<String, Node> nodes = nets.get(netId);
		if(nodes == null){
			nodes = new HashMap<String, Node>();
			nets.put(netId, nodes);
		}
		return nodes;
	}
	
	void log(String s){
		//System.out.println(s);
		LOGGER.warning(s);
	}
	
	//For testing
//	void addNode0(){
//		NodeState state = new NodeState();
//		LatLonPosition p = new LatLonPosition(0, 0);
//		state.setPosition(p);
//		state.setValues(new ArrayList());
//		state.setSensors(new ArrayList());
//		nodes.put("0", new Node("0", state));
//	}
}
