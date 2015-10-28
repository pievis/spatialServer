package it.isac.commons.requestresponse;

import it.isac.commons.interfaces.IData;

public class GenericData implements IData {

	String type;
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}
	
}
