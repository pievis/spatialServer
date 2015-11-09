package it.isac.client.interfaces.managers;

import java.util.List;
import java.util.Map;

import it.isac.client.impl.device.FieldCalculusFunction;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;

public interface IComputationManager {
	public void addField(FieldCalculusFunction function);
	public INodeValue getCurrentState(String id);
	public Map<String, List<INodeValue>> getNeighborhood();
	public Map<String, ISensorSnapshot> getSensorsValue();
}
