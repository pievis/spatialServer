package it.isac.client.impl.sensors;

import it.isac.client.interfaces.sensors.IConcreteSensor;

//Simple Mock Sensor, just for test
public class MockSensor implements IConcreteSensor {
	private int count = 0;
	
	@Override
	public String getValue() {
		count++;
		return Integer.toString(count);
	}

}
