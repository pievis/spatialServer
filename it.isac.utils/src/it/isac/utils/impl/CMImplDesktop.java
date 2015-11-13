package it.isac.utils.impl;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;

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
		System.out.println("join network start");
		ClientResource service = new ClientResource(ComManagerFactory.BASEURL);
//				ComManagerFactory.NETID
//				+ "/nodes/"); //TODO Change
		
		
		INodesResource nodesRe = service.getChild(ComManagerFactory.NETID
				+ "/nodes/", INodesResource.class);
		System.out.println(service
				.getChild(ComManagerFactory.NETID + "/nodes/").getReference()
				.toString());
		String idNode = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(state);
//			System.out.println(json);
//			Representation rep = new StringRepresentation(json);
//			service.post(rep, MediaType.APPLICATION_JSON);
		} catch (JsonProcessingException e1) {
			System.out.println("Errore: ");
			e1.printStackTrace();
		}
		try {
			SimpleResponse sr = nodesRe.addNode(state); // POST
			 System.out.println(sr.getMessage());
			if (sr.isSuccess()) {
				idNode = ((IdClass) sr.getData()).getId();
			} else {
				System.out.println("Something wrong during joining.\n"
						+ sr.getMessage());
			}
			System.out.println("Network joined");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idNode;
	}

	public NodeList fetchNeighbour(String nodeId) {
		ClientResource service = new ClientResource(ComManagerFactory.BASEURL);
		INeighboursResource nbrRes = service.getChild(ComManagerFactory.NETID
				+ "/nodes/" + nodeId + "/nbr/", INeighboursResource.class);
		NodeList res = nbrRes.represent(); // GET
		System.out.println("Nbr fetched");
		return res;
	}

	public void sendState(String nodeId, NodeState state) {
		ClientResource service = new ClientResource(ComManagerFactory.BASEURL);
		INodeResource nodesRes = service.getChild(ComManagerFactory.NETID
				+ "/nodes/" + nodeId, INodeResource.class);
		SimpleResponse sr = nodesRes.update(new Node(nodeId, state)); // POST
		if (sr != null) {
			if (!sr.isSuccess())
				System.out.println("Error while sending new state: "
						+ sr.getMessage());
		} else
			System.out
					.println("Something wrong with the server: external server error");
		System.out.println("State sent");
	}

}
