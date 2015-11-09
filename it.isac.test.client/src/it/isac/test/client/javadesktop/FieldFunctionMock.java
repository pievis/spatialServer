package it.isac.test.client.javadesktop;

import java.util.List;
import java.util.Map;

import it.isac.client.impl.device.FieldCalculusFunction;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.nodevalues.BasicNodeValue;

public class FieldFunctionMock extends FieldCalculusFunction {

	public FieldFunctionMock(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public INodeValue compute(INodeValue localCurrentStates, Map<String, List<INodeValue>> nbrState,
			Map<String, ISensorSnapshot> localSensors) {
		// Print everything
		System.out.println("My Current value: ");
		System.out.println("Id: "+localCurrentStates.getKey());
		System.out.println("Value: "+localCurrentStates.getValue());
		System.out.println();
		System.out.println("Sensor Value: ");
		for(String k : localSensors.keySet()) {
			System.out.println("Key: "+ k);
			System.out.println("Id: "+localSensors.get(k).getSensorId());
			System.out.println("Type: "+localSensors.get(k).getType());
			System.out.println("Value: "+localSensors.get(k).getValue());
		}
		return new BasicNodeValue(localCurrentStates.getKey(), localCurrentStates.getValue());
	}

}