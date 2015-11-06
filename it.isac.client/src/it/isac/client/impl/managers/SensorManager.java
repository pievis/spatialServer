package it.isac.client.impl.managers;

import it.isa.commons.model.sensors.SensorCounterMock;
import it.isac.client.interfaces.managers.ISensorManager;
import it.isac.commons.interfaces.ISensor;

public class SensorManager extends AbstractManager implements ISensorManager {
	//private List<ISensor> sensors;
	
	protected SensorManager(Long initFreq) {
		super(initFreq);
	}

	@Override
	public void addSensor(ISensor sensor) {
		// add a worker
		this.workers.add(new SensorWorker(sensor));
	}

	@Override
	public void addSimulatedSensor(ISensor sensor) {
		// add a worker (with simulated sensor)
		this.workers.add(new SensorWorker(sensor));
	}
	
	public static void main(String[] args) {
		// just for test
		Long frequency = new Long(1000);
		SensorManager sensMng = new SensorManager(frequency);
		SensorCounterMock mock = new SensorCounterMock("mock0");
		mock.startCounting(2);
		SensorCounterMock mock2 = new SensorCounterMock("mock1");
		mock2.startCounting(3);
		sensMng.addSensor(mock);
		sensMng.addSensor(mock2);
		sensMng.start();
	}
}
