package it.isac.client.interfaces.managers;

import it.isac.commons.model.NodeList;
import it.isac.commons.model.NodeState;

public interface INetworkManager {
	public NodeState getCurrentState();
	public void setNeighbourhood(NodeList neighbourhood);
}
