package it.isac.server.testing;


import java.util.logging.Logger;

import it.isac.commons.interfaces.resources.INeighboursResource;
import it.isac.commons.interfaces.resources.INodeResource;
import it.isac.commons.interfaces.resources.INodesResource;
import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeList;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.XYPosition;
import it.isac.commons.requestresponse.IdClass;
import it.isac.commons.requestresponse.SimpleResponse;

import org.restlet.resource.ClientResource;


/**
 * It's only used to make some test, don't expect anything much.
 * @author Pievis
 */
public class ClientTest1 {
	
	final static Logger LOGGER = Logger.getLogger("test");
	
	public static void main(String[] args){
		//addNewNodeXY(2.0, 1.0);
		addNode0();
		//Test1();
		testNbr();
	}
	
	static void addNode0(){
		NodeState state = new NodeState();
		state.setPosition(new XYPosition(1.1, 1.2));
		Node node = new Node("0", state);
		ClientResource service = new ClientResource("http://localhost:8111");
		INodeResource nodeRes = service.getChild("/net0/nodes/0/", INodeResource.class);
		nodeRes.update(node); //added the node
		log("node 0 added");
	}
	
	//SearchCriteria sc = new RangeSearch(10.0, Unit.M);
	static void testNbr(){
		String id = addNewNodeXY(10.0, 5.0);
		addNewNodeXY(15.0, 5.5);
		addNewNodeXY(40.0, 5.0);
		log("----------------");
		ClientResource service = new ClientResource("http://localhost:8111");
		INeighboursResource nbrRes = service.getChild("/net0/nodes/" + id + "/nbr/",
				INeighboursResource.class);
		NodeList nodes = nbrRes.represent();
		for(Node n : nodes){
			log("\t" + n.getId());
			log("\t\t" + n.getState().toString());
		}
	}
	
	//Testing resource Nodes
	static String addNewNodeXY(double x, double y){
		ClientResource service = new ClientResource("http://localhost:8111");
		INodesResource nodesRes = service.getChild("/net0/nodes/", INodesResource.class);
		SimpleResponse sr = nodesRes.addNode(getNodeState(x, y));
		try{
			if(sr.isSuccess()){
				String id = ((IdClass)sr.getData()).getId();
				log("Node added\nID:" + id);
				return id;
			}
			else
				log("Server found an error: " + sr.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	static void printNodesInTheNet(){
		ClientResource service = new ClientResource("http://localhost:8111");
		INodesResource nodesRes = service.getChild("/net0/nodes/", INodesResource.class);
		NodeList nodes = nodesRes.represent();
		for(Node n : nodes){
			log("\t" + n.getId());
			log("\t\t" + n.getState().toString());
		}
	}
	
	//Testing resource Node
	static void Test1(){
		ClientResource service = new ClientResource("http://localhost:8111");
		
		INodeResource node0re = service.getChild("/net0/nodes/0/", INodeResource.class);
		Node node0 = node0re.represent();
		log("Node:");
		log("Id: " + node0.getId());
		log("Position: " + node0.getState().getPosition().toString());
		
		log("Update node status - ");
		NodeState ns0 = new NodeState();
		XYPosition oldPos = (XYPosition)node0.getState().getPosition();
		XYPosition pos = new XYPosition(1.2 + oldPos.getX() , 10 + oldPos.getY());
		ns0.setPosition(pos);
		node0.setState(ns0);
		//PUT
		SimpleResponse sr = node0re.update(node0);
		if(sr.isSuccess())
			log("node updated " + sr.getMessage());
		else
			log(sr.getMessage());
		
		node0 = node0re.represent();
		log("Node:");
		log("Id: " + node0.getId());
		log("Position: " + node0.getState().getPosition().toString());
	}
	
	static NodeState getNodeState(double x, double y){
		NodeState ns0 = new NodeState();
		XYPosition pos = new XYPosition(x,y);
		ns0.setPosition(pos);
		return ns0;
	}
	
	static void log(String s){
		LOGGER.info(s);
	}

}
