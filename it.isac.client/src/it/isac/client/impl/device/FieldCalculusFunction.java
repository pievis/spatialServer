package it.isac.client.impl.device;

import java.util.List;
import java.util.Map;

import javax.naming.NotContextException;

import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;

public abstract class FieldCalculusFunction {
	protected ExecutionContext<?> execContext;
	protected String fieldId;

	public FieldCalculusFunction(String name) {
		this.fieldId = name;
	}

	public void setExecutionContext(ExecutionContext<?> execContext) {
		this.execContext = execContext;
	}

	protected ExecutionContext<?> getExecutionContext() throws NotContextException {
		if (execContext == null)
			throw new NotContextException();
		return this.execContext;
	}
	
	public String getFieldId() {
		return fieldId;
	}

	// Neighbor is a map of NodeId - List<State> because every nove can have
	// multiple field
	public abstract INodeValue compute(INodeValue localCurrentStates, Map<String, List<INodeValue>> nbrState,
			Map<String, ISensorSnapshot> localSensors);
}
