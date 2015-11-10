package it.isac.client.impl.managers;

import java.util.List;
import java.util.Map;

import it.isac.client.impl.device.FieldCalculusFunction;
import it.isac.client.interfaces.managers.IComputationManager;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;

public class ComputationWorker extends DeviceJobWorker {
	FieldCalculusFunction func;
	
	public ComputationWorker(String id, AbstractManager manager, FieldCalculusFunction function) {
		super(id, manager);
		this.func = function;
	}

	@Override
	public void doJob() {
		// Current state: many field, each with its id
		INodeValue currentState = ((IComputationManager)mng).getCurrentState(this.id);
		// Neighbor State: pair NodeId, list of field
		Map<String, List<INodeValue>> nbrState = ((IComputationManager)mng).getNeighborhood();
		// Sensors: SensorID, value
		Map<String, ISensorSnapshot> localSensors =((IComputationManager)mng).getSensorsValue();
		// compute
		INodeValue value = func.compute(currentState, nbrState, localSensors);
		this.val = value; // update new state
	}

}
