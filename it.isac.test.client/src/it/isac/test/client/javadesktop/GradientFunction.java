package it.isac.test.client.javadesktop;

import java.util.Map;

import javax.naming.NotContextException;

import it.isac.client.impl.device.Domain;
import it.isac.client.impl.device.ExecutionContext;
import it.isac.client.impl.device.FieldCalculusFunction;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.nodevalues.BasicNodeValue;

public class GradientFunction extends FieldCalculusFunction {

	public GradientFunction(INodeValue startingValue) {
		super(startingValue);
	}

	@Override
	public INodeValue compute(INodeValue localCurrentStates,
			Map<String, NodeState> nbrState,
			Map<String, ISensorSnapshot> localSensors) {
		INodeValue nextValue = new BasicNodeValue(localCurrentStates.getKey());
		ISensorSnapshot source = null;
		// find the source sensor
		for (String k : localSensors.keySet()) {
			if (localSensors.get(k) != null) {
				if (localSensors.get(k).getSensorId() == "source")
					source = localSensors.get(k);
			} else {
				nextValue.setValue("Error");
			}
		}
		// is source
		ExecutionContext<?> exec = null;
		try {
			exec = getExecutionContext();
		} catch (NotContextException e) {
			nextValue.setValue("Error");
			e.printStackTrace();
		}
		if (Boolean.parseBoolean(source.getValue())) {
			nextValue.setValue("0"); // distance 0
		} else { // isn't source
			String keyMinNbr = "";
			double minDist = Double.MAX_VALUE;
			// find the node with min distance
			for (String k : nbrState.keySet()) {
				if (k != exec.getNodeId()) {
					NodeState ns = nbrState.get(k);
					double dist = Double.MAX_VALUE;
					for (INodeValue v : ns.getValues()) {
						if (v.getKey() == "distGrad") // find the correct value
							dist = Double.parseDouble(v.getValue());
					}
					if (dist < minDist) {
						minDist = dist;
						keyMinNbr = k;
					}
				}
			}
			if (keyMinNbr.isEmpty())
				nextValue.setValue("inf");
			else {
				if (exec != null)
					nextValue.setValue(minDist
							+ Double.toString(exec.distanceTo(keyMinNbr)));
			}
		}
		return nextValue;
	}
}
