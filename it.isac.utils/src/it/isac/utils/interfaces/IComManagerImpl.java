package it.isac.utils.interfaces;

import it.isac.commons.model.NodeList;
import it.isac.commons.model.NodeState;

public interface IComManagerImpl {
	public String joinNetwork(NodeState state);
	public NodeList fetchNeighbour(String nodeId);
	public void sendState(String nodeId, NodeState state);
}
