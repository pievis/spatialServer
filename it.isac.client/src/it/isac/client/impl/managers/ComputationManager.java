package it.isac.client.impl.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.isac.client.impl.device.Domain;
import it.isac.client.impl.device.FieldCalculusFunction;
import it.isac.client.interfaces.managers.IComputationManager;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.Node;

public class ComputationManager extends AbstractManager implements IComputationManager {

	int nextWorkerId = 0;
	
	protected ComputationManager(Long initFreq) {
		super(initFreq);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateValue(String id, Object value) {
		// update a single field
		INodeValue field = (INodeValue)value;
		Domain.getIstance().updateFieldValue(field.getKey(), field);		
	}

	@Override
	public void addField(FieldCalculusFunction function) {
		// TODO who decide the id of the field??
		this.workers.add(new ComputationWorker("field"+nextWorkerId, this, function));
		nextWorkerId++;
		
	}

	@Override
	public INodeValue getCurrentState(String id) {
		return Domain.getIstance().getFieldValue(id);
	}

	@Override
	public Map<String, List<INodeValue>> getNeighborhood() {
		HashMap<String, Node> completeNbr = Domain.getIstance().getAllNbr();
		HashMap<String, List<INodeValue>> nbr = new HashMap<>();
		for(String k : completeNbr.keySet())
			nbr.put(k, completeNbr.get(k).getState().getValues());
		
		return nbr;
	}

	@Override
	public Map<String, ISensorSnapshot> getSensorsValue() {
		return Domain.getIstance().getAllSensorValue();
	}

}
