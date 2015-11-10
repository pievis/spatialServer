package it.isac.utils.impl;

import it.isac.commons.model.NodeList;
import it.isac.commons.model.NodeState;
import it.isac.utils.interfaces.IComManagerImpl;
import it.isac.utils.interfaces.IComunicationManager;

public class ComunicationManager implements IComunicationManager {
	
	private IComManagerImpl CMImpl;
	
	public ComunicationManager(IComManagerImpl impl) {
		CMImpl = impl;
	}
	
	public String joinNetwork(NodeState state) {
		return CMImpl.joinNetwork(state);
	}
	
	public void sendState(String nodeId, NodeState state) {
		CMImpl.sendState(nodeId, state);
	}
	
	public NodeList fetchNeighbour(String nodeId) {
		return CMImpl.fetchNeighbour(nodeId);
	}
}
