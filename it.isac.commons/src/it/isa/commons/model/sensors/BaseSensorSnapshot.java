package it.isa.commons.model.sensors;

import it.isac.commons.interfaces.ISensorSnapshot;

public class BaseSensorSnapshot implements ISensorSnapshot{
	
	String value;
	String type;
	String sensorId;
	
	public BaseSensorSnapshot(){}
	public BaseSensorSnapshot(String sensorId, String type, String value){
		this.value = value;
		this.sensorId = sensorId;
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSensorId() {
		return sensorId;
	}
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
		
}
