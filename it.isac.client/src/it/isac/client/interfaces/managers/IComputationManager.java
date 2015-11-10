package it.isac.client.interfaces.managers;

import java.util.Map;

import it.isac.client.impl.device.FieldCalculusFunction;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.NodeState;

public interface IComputationManager {
	public void addField(FieldCalculusFunction function);
	public INodeValue getCurrentState(String id);
	public Map<String, NodeState> getNeighborhood();
	public Map<String, ISensorSnapshot> getSensorsValue();
}
