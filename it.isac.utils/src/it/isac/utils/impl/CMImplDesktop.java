package it.isac.utils.impl;

import it.isac.commons.model.NodeState;
import it.isac.utils.interfaces.ICMImplDesktop;

public class CMImplDesktop extends ComManagerImpl implements ICMImplDesktop {
	//TODO: Actually this class act like a mock. We will consider a future implementation
	
	@Override
	public void joinNetwork(NodeState state) {
		System.out.println("Mock join Network");
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
