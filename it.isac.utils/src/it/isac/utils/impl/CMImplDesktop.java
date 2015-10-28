package it.isac.utils.impl;

import org.restlet.resource.ClientResource;
import it.isac.commons.requestresponse.IdClass;
import it.isac.commons.interfaces.resources.INodesResource;
import it.isac.commons.model.NodeState;
import it.isac.commons.requestresponse.SimpleResponse;
import it.isac.utils.interfaces.ICMImplDesktop;

public class CMImplDesktop extends ComManagerImpl implements ICMImplDesktop {
	// TODO: Actually this class act like a mock. We will consider a future
	// implementation

	@Override
	public void joinNetwork(NodeState state) {
		ClientResource service = new ClientResource("http://localhost:8111");
		INodesResource nodesRe = service.getChild("/net0/nodes/", INodesResource.class);
		SimpleResponse sr = nodesRe.addNode(state); // POST
		
		String idNode = "";
		System.out.println(sr.getMessage());
		if(sr.isSuccess()) {
			idNode = ((IdClass)sr.getData()).getId();
		}
		
		System.out.println("Mock join Network - idNode: " + idNode);
	}

	@Override
	public void fetchNeighbour(String nodeId) {
		System.out.println("Mock fetch Nbr");
	}

	@Override
	public void sendState(String nodeId, NodeState state) {
		System.out.println("Mock send state");
	}

}
