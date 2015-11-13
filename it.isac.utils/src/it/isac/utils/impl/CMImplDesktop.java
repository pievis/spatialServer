package it.isac.utils.impl;

import org.restlet.resource.ClientResource;

import it.isac.commons.requestresponse.IdClass;
import it.isac.commons.interfaces.resources.INeighboursResource;
import it.isac.commons.interfaces.resources.INodeResource;
import it.isac.commons.interfaces.resources.INodesResource;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeList;
import it.isac.commons.model.NodeState;
import it.isac.commons.requestresponse.SimpleResponse;
import it.isac.utils.interfaces.ICMImplDesktop;

public class CMImplDesktop extends ComManagerImpl implements ICMImplDesktop {

	public String joinNetwork(NodeState state) {
		// Join network service
		ClientResource service = new ClientResource(ComManagerFactory.BASEURL);
		INodesResource nodesRe = service.getChild(ComManagerFactory.NETID + "/nodes/", INodesResource.class);
		String idNode = "";
		try {
			// addNode without id will create a new node
			SimpleResponse sr = nodesRe.addNode(state); // POST
			// check
			if (sr.isSuccess())
				idNode = ((IdClass) sr.getData()).getId();
			else
				System.err.println("Something wrong during joining.\n" + sr.getMessage());

			//System.out.println("Network joined");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idNode;
	}

	public NodeList fetchNeighbour(String nodeId) {
		ClientResource service = new ClientResource(ComManagerFactory.BASEURL);
		INeighboursResource nbrRes = service.getChild(ComManagerFactory.NETID + "/nodes/" + nodeId + "/nbr/",
				INeighboursResource.class);
		NodeList res = nbrRes.represent(); // GET
		//System.out.println("Nbr fetched");
		return res;
	}

	public void sendState(String nodeId, NodeState state) {
		ClientResource service = new ClientResource(ComManagerFactory.BASEURL);
		INodeResource nodesRes = service.getChild(ComManagerFactory.NETID + "/nodes/" + nodeId+"/", INodeResource.class);
		// update node state with id = nodeId
		try {
		SimpleResponse sr = nodesRes.update(new Node(nodeId, state)); // POST
		// check
		if (sr != null) {
			if (!sr.isSuccess())
				System.err.println("Error while sending new state: " + sr.getMessage());
		} else
			System.err.println("Something wrong with the server: external server error");
			//System.out.println("State sent");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
