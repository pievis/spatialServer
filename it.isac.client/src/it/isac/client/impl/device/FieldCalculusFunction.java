package it.isac.client.impl.device;

import java.util.Map;

import javax.naming.NotContextException;

import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.NodeState;

public abstract class FieldCalculusFunction {
	private ExecutionContext<?> execContext;
	private INodeValue startVal;

	public FieldCalculusFunction(INodeValue startingValue) {
		this.startVal = startingValue;
	}

	public void setExecutionContext(ExecutionContext<?> execContext) {
		this.execContext = execContext;
	}

	protected ExecutionContext<?> getExecutionContext() throws NotContextException {
		if (execContext == null)
			throw new NotContextException();
		return this.execContext;
	}
	
	public INodeValue getStarting() {
		return startVal;
	}

	// Neighbor is a map of NodeId - NodeState because every node can have
	// multiple field
	public abstract INodeValue compute(INodeValue localCurrentStates, Map<String, NodeState> nbrState,
			Map<String, ISensorSnapshot> localSensors);
}
