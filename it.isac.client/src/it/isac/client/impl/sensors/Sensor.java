package it.isac.client.impl.sensors;

import it.isac.client.interfaces.sensors.IConcreteSensor;
import it.isac.client.interfaces.sensors.ISensor;

public class Sensor implements ISensor {
	
	IConcreteSensor sensor; //concrete sensor implementation
	
	public Sensor(IConcreteSensor impl) {
		sensor = impl; //set the implementation
	}

	@Override
	public String getValue() {
		if(sensor != null)
			return sensor.getValue();
		else
			return "";
	}

}
