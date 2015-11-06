package it.isac.client.impl.managers;

import it.isac.client.impl.sensors.Sensor;

import java.util.List;

public class SensorWorker extends DeviceJobWorker {
	List<Sensor> sensors;
	
	public SensorWorker(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	@Override
	public void doJob() {
		// get the values from every sensors
		for(Sensor s : sensors) {
			String sensorValue = s.getValue();
			//TODO: Do something with those values!
			System.out.println(sensorValue);
		}
	}

}
