package it.isac.utils.interfaces;
import it.isac.commons.model.NodeState;

public interface IComunicationManager {
	public void joinNetwork(NodeState state);
	public void fetchNeighbour(String nodeId);
	public void sendState(String nodeId, NodeState state);
	
}
