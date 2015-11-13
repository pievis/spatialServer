package it.isac.client.impl.managers;

import it.isac.client.impl.device.Domain;
import it.isac.client.interfaces.managers.ISensorManager;
import it.isac.commons.interfaces.IPosition;
import it.isac.commons.interfaces.ISensor;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.Position;
import it.isac.commons.model.sensors.SensorCounterMock;
import it.isac.commons.model.sensors.SensorType;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SensorManager extends AbstractManager implements ISensorManager {
	// private List<ISensor> sensors;
	int nextWorkerId = 0;

	public SensorManager(Long initFreq) {
		super(initFreq);
	}

	@Override
	public void addSensor(ISensor sensor) {
		// add a worker
		this.workers.add(new SensorWorker(sensor, "sensor" + nextWorkerId, this));
		nextWorkerId++;
	}

	@Override
	public void addSimulatedSensor(ISensor sensor) {
		// add a worker (with simulated sensor)
		this.workers.add(new SensorWorker(sensor, "sensor" + nextWorkerId, this));
		nextWorkerId++;
	}

	@Override
	public void updateValue(String workerId, Object value) {
		// Handle update of the domain
		ISensorSnapshot sensorVal = (ISensorSnapshot) value;
		if (sensorVal.getType().contains(SensorType.GPS)) {
			// special case for GPS sensor
			IPosition position = null;
			// let jackson mapper do the conversion
			ObjectMapper mapper = new ObjectMapper();
			try {
				position = mapper.readValue(sensorVal.getValue(), IPosition.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if (position != null)
				Domain.getIstance().setPosition(position);
		}
		// note that workerId is used in place of sensorId
		Domain.getIstance().updateSensorValue(workerId, sensorVal);

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
