package it.isac.commons.model.sensors;

import it.isac.commons.interfaces.ISensor;
import it.isac.commons.interfaces.ISensorSnapshot;

public class SensorSource implements ISensor {
	String id;
	boolean active;
	
	public SensorSource(String sensorId) {
		this.id = sensorId;
	}
	
	public void setOn() {
		active = true;
	}
	public void setOff() {
		active = false;
	}

	@Override
	public ISensorSnapshot getValue() {
		return new BaseSensorSnapshot(getId(), getType(), Boolean.toString(active));
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getType() {
		return SensorType.SOURCE;
	}

}
