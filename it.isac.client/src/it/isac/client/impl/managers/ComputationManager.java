package it.isac.client.impl.managers;

import java.util.Map;

import it.isac.client.impl.device.Domain;
import it.isac.client.impl.device.FieldCalculusFunction;
import it.isac.client.interfaces.managers.IComputationManager;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.NodeState;

public class ComputationManager extends AbstractManager implements IComputationManager {

	int nextWorkerId = 0;
	
	public ComputationManager(Long initFreq) {
		super(initFreq);
	}

	@Override
	public void updateValue(String workerId, Object value) {
		// update a single field
		Domain.getIstance().updateFieldValue(workerId, (INodeValue)value);		
	}

	@Override
	public void addField(FieldCalculusFunction function) {
		String workerId = "field"+nextWorkerId;
		// set the staring value
		Domain.getIstance().updateFieldValue(workerId,function.getStarting());
		this.workers.add(new ComputationWorker(workerId, this, function));
		nextWorkerId++;
	}

	@Override
	public INodeValue getCurrentState(String id) {
		return Domain.getIstance().getFieldValue(id);
	}

	@Override
	public Map<String, NodeState> getNeighborhood() {
		return Domain.getIstance().getAllNbr();
	}

	@Override
	public Map<String, ISensorSnapshot> getSensorsValue() {
		return Domain.getIstance().getAllSensorValue();
	}

}
