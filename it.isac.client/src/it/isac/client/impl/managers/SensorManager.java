package it.isac.client.impl.managers;

import it.isac.client.impl.device.Domain;
import it.isac.client.interfaces.managers.ISensorManager;
import it.isac.commons.interfaces.IPosition;
import it.isac.commons.interfaces.ISensor;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.PositionType;
import it.isac.commons.model.sensors.SensorCounterMock;
import it.isac.commons.model.sensors.SensorType;

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
	public void updateValue(String id, Object value) {
		// Handle update of the domain
		ISensorSnapshot sensorVal = (ISensorSnapshot) value;
		if (sensorVal.getType().contains(SensorType.GPS)) {
			System.out.println("GPS received");
			// special case for GPS sensor
			String posType = sensorVal.getType().replace(SensorType.GPS, "");
			System.out.println("Pos type: "+posType);
			// let jackson mapper do the conversion
			try {
				ObjectMapper mapper = new ObjectMapper();
			}
			catch (Exception e) {
				System.out.println("Sono un eccezione di m****");
				e.printStackTrace(System.out);
			}
			IPosition position = null;
			System.out.println("GPS VALUE: "+sensorVal.getValue());
			try {
				switch (posType) {
				case PositionType.LATLON:
					//position = mapper.readValue(sensorVal.getValue(), LatLonPosition.class);
					break;
				case PositionType.XY:
					System.out.println("XY POS: "+sensorVal.getValue());
					//position = mapper.readValue(sensorVal.getValue(), XYPosition.class);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
			if (position != null)
				Domain.getIstance().setPosition(position);
		}
		// note that sensorId is used in place of workerId
		Domain.getIstance().updateSensorValue(sensorVal.getSensorId(), sensorVal);
		
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
