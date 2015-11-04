package it.isac.server.testing;

import it.isac.commons.interfaces.resources.INeighboursResource;
import it.isac.commons.interfaces.resources.INodeResource;
import it.isac.commons.interfaces.resources.INodesResource;
import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeList;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.Unit;
import it.isac.commons.requestresponse.IdClass;
import it.isac.commons.requestresponse.SimpleResponse;

import java.util.logging.Logger;

import org.restlet.resource.ClientResource;

/**
 * @author Pievis
 *
 */
public class ClientTest2 {
	final static Logger LOGGER = Logger.getLogger("test");

	public static void main(String[] args) {
		Test1();
	}

	static void Test1() {
		log("*Adding nodes*");
		// Piazza del popolo 44.137199, 12.241922
		LatLonPosition piazza = new LatLonPosition(44.137199, 12.241922);
		String idPiazza = addNewNode(44.137199, 12.241922);
		// Piazza (pappa reale) 44.137560, 12.241320
		addNewNode(44.137560, 12.241320);
		// Rocca 44.135996, 12.240365.
		addNewNode(44.135996, 12.240365);
		// via chiaramonti (biblioteca) 44.140027, 12.242564
		addNewNode(44.140027, 12.242564);
		// via sacchi 3 (facoltà) 44.139623, 12.243427
		addNewNode(44.139623, 12.243427);
		//Nodi lontani
		//Stazione 44.145154, 12.249463
		//Maglie 40.118341, 18.298905
		//Lecce 40.352647, 18.145321

		log("*Punti vicini piazza del popolo*"); // def 100 m
		NodeList nodes = nbr(idPiazza);
		for (Node n : nodes) {
			log("\t" + n.getId());
			log("\t\t" + n.getState().toString());
			LatLonPosition p = (LatLonPosition) n.getState().getPosition();
			log("\tDistance: " + LatLonPosition.distance(piazza, p, Unit.M));
		}
		//
		log("Testing distances");
		LatLonPosition p = new LatLonPosition(44.137560, 12.241320);
		log("\tDistance from pappa reale: " + LatLonPosition.distance(piazza, p, Unit.M));
		p = new LatLonPosition(44.140027, 12.242564);
		log("\tDistance from via chiaramonti: " + LatLonPosition.distance(piazza, p, Unit.M));
	}

	static NodeList nbr(String id) {
		ClientResource service = new ClientResource("http://localhost:8111");
		INeighboursResource nbrRes = service.getChild("/net0/nodes/" + id
				+ "/nbr/", INeighboursResource.class);
		NodeList nodes = nbrRes.represent();
		return nodes;
	}

	static String addNewNode(double lat, double lon) {
		ClientResource service = new ClientResource("http://localhost:8111");
		INodesResource nodesRes = service.getChild("/net0/nodes/",
				INodesResource.class);
		NodeState nodeState = new NodeState();
		nodeState.setPosition(new LatLonPosition(lat, lon));
		SimpleResponse sr = nodesRes.addNode(nodeState);
		try {
			if (sr.isSuccess()) {
				String id = ((IdClass) sr.getData()).getId();
				log("Node added\nID:" + id);
				return id;
			} else
				log("Server found an error: " + sr.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	static void updateNode(String id, NodeState newState) {
		ClientResource service = new ClientResource("http://localhost:8111");
		INodeResource nodeRes = service.getChild("/net0/nodes/" + id,
				INodeResource.class);
		SimpleResponse sr = nodeRes.update(new Node(id, newState));
		if (!sr.isSuccess())
			log("Error updating " + id);
	}

	static void log(String s) {
		LOGGER.info(s);
	}
}
