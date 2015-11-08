package it.isac.commons.model.nodevalues;

import it.isac.commons.interfaces.INodeValue;

// Basic value (i.e. int, double, string, ...) of a node
public class BasicNodeValue implements INodeValue {
	String value;
	protected String key;
	
	public BasicNodeValue(String key) {
		this.key = key;
	}
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}

}
