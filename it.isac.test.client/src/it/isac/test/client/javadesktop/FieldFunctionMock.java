package it.isac.test.client.javadesktop;

import java.util.Map;

import it.isac.client.impl.device.FieldCalculusFunction;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.nodevalues.BasicNodeValue;
import it.isac.commons.model.sensors.SensorType;

public class FieldFunctionMock extends FieldCalculusFunction {
	String startingValue;

	public FieldFunctionMock(INodeValue startValue) {
		super(startValue);
	}

	@Override
	public INodeValue compute(INodeValue localCurrentStates, Map<String, NodeState> nbrState,
			Map<String, ISensorSnapshot> localSensors) {
		
		int i = Integer.parseInt(localCurrentStates.getValue());
		i++;
		// With observer, System out no more!
		
		// Print everything
		// System.out.println("My Current value: ");
		// System.out.println("Id: " + localCurrentStates.getKey());
		// System.out.println("Value: " + localCurrentStates.getValue());
		// System.out.println();
		//
//		 String nbrVal ="NbrValue: ";
//		 for(String k : nbrState.keySet()) {
//			 nbrVal+="Key:"+k;
//			 nbrVal +=nbrState.get(k).toString()+"\n";
//		 }
//		 System.out.println(nbrVal);
		// System.out.println();
		//
		// System.out.println("Sensor Value: ");
		// for (String k : localSensors.keySet()) {
		// System.out.println("Key: " + k);
		// System.out.println("Id: " + localSensors.get(k).getSensorId());
		// System.out.println("Type: " + localSensors.get(k).getType());
		// System.out.println("Value: " + localSensors.get(k).getValue());
		// }
//		for (String k : localSensors.keySet()) {
//			if (localSensors.get(k).getType() == SensorType.GPS)
//				System.out.println("Position: " + localSensors.get(k).getValue());
//		}
		//System.out.println();
		return new BasicNodeValue(localCurrentStates.getKey(), i+"");
	}
}
