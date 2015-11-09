package it.isac.client.impl.managers;

import it.isac.commons.interfaces.ISensor;
import it.isac.commons.interfaces.ISensorSnapshot;

public class SensorWorker extends DeviceJobWorker {
	ISensor sensor;
	
	public SensorWorker(ISensor sensor, String id, SensorManager mng) {
		super(id, mng);
		this.sensor = sensor;
	}

	@Override
	public void doJob() {
		// get sensor value
		ISensorSnapshot sensorValue = sensor.getValue();
		this.val = sensorValue;
		//TODO: Do something with those values!
		//System.out.println(sensorValue.getSensorId() + "_" + sensorValue.getValue());
		
	}

}
