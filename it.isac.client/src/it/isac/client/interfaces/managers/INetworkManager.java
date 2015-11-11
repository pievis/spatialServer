package it.isac.client.interfaces.managers;

import it.isac.commons.model.NodeState;

public interface INetworkManager {
	// Network manager give no ways to add or remove workers from an external
	// object
	public NodeState getCurrentState();
	// neighbourhood will be set from updateValue method (inherited from
	// AbstractManager)
	// public void setNeighbourhood(NodeList neighbourhood);
}
