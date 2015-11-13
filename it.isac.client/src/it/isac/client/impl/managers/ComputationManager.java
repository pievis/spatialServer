package it.isac.client.impl.managers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observer;

import it.isac.client.impl.device.Domain;
import it.isac.client.impl.device.FieldCalculusFunction;
import it.isac.client.interfaces.managers.IComputationManager;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.NodeState;

public class ComputationManager extends AbstractManager implements IComputationManager {

	ArrayList<Observer> observers;

	public ComputationManager(Long initFreq) {
		super(initFreq);
		observers = new ArrayList<>();
	}

	@Override
	public void updateValue(String workerId, Object value) {
		if (value != null) {
			// update a single field
			Domain.getIstance().updateFieldValue(workerId, (INodeValue) value);
			notifyChange(workerId);
		}
	}

	@Override
	public void addField(FieldCalculusFunction function, String functionId) {
		// set the staring value
		Domain.getIstance().updateFieldValue(functionId, function.getStarting());
		this.workers.add(new ComputationWorker(functionId, this, function));

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

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyChange(String fieldId) {
		for (Observer e : observers)
			e.update(null, fieldId);
	}

}
