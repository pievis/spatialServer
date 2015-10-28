package it.isac.utils.impl;

import it.isac.commons.model.NodeState;
import it.isac.utils.interfaces.IComManagerImpl;
import it.isac.utils.interfaces.IComunicationManager;

public class ComunicationManager implements IComunicationManager {
	
	private IComManagerImpl CMImpl;
	
	public ComunicationManager(IComManagerImpl impl) {
		CMImpl = impl;
	}
	
	public void joinNetwork(NodeState state) {
		CMImpl.joinNetwork(state);
	}
	
	public void sendState(String nodeId, NodeState state) {
		CMImpl.sendState(nodeId, state);
	}
	
	public void fetchNeighbour(String nodeId) {
		CMImpl.fetchNeighbour(nodeId);
	}
}
