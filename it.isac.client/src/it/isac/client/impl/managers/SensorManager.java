package it.isac.client.impl.managers;

import java.util.ArrayList;
import java.util.List;

import it.isac.client.impl.sensors.MockSensor;
import it.isac.client.impl.sensors.Sensor;
import it.isac.client.interfaces.managers.ISensorManager;
import it.isac.client.interfaces.sensors.ISensor;

public class SensorManager extends AbstractManager implements ISensorManager {
	private List<Sensor> sensors;
	
	protected SensorManager(Long initFreq) {
		super(initFreq);
		sensors = new ArrayList<Sensor>();
		// add a worker
		this.workers.add(new SensorWorker(sensors));
	}

	@Override
	public void addSensor(ISensor sensor) {
		sensors.add((Sensor)sensor);
	}

	@Override
	public void addSimulatedSensor(ISensor sensor) {
		sensors.add((Sensor)sensor);
	}
	
	public static void main(String[] args) {
		// just for test
		Long frequency = new Long(1000);
		SensorManager sensMng = new SensorManager(frequency);
		Sensor mock = new Sensor(new MockSensor());
		sensMng.addSensor(mock);
		sensMng.start();
	}
}
