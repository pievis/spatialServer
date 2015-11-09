package it.isac.client.impl.device;

import it.isac.client.impl.managers.SensorManager;
import it.isac.client.interfaces.device.IDevice;
import it.isac.commons.interfaces.ISensor;

public class Device implements IDevice {
	
	Long freq; // scheduling frequency of the workers
	SensorManager sensorMng;
	
	public Device(Long frequency) {
		this.freq = frequency;
		init();
	}

	private void init() {
		sensorMng = new SensorManager(freq);
	}

	@Override
	public void start() {
		// Tell to every manager to start their worker
		sensorMng.start();
	}
	@Override
	public void stop() {
		// Tell to every manager to stop (pause) their worker
		sensorMng.stop();
	}
	@Override
	public void dispose() {
		// Tell to every manager to dispose (halt) their worker
		sensorMng.dispose();

	}

	@Override
	public void addRealSensor(ISensor sensor) {
		// add a real sensor
		sensorMng.addSensor(sensor);
	}
	@Override
	public void addSimulatedSensor(ISensor sensor) {
		// add a simulated sensor
		sensorMng.addSimulatedSensor(sensor);
		// no difference between simulated and real sensor
		// reserved for future use
	}

}
