package it.isac.test.client.javadesktop;

import it.isac.client.impl.device.Device;
import it.isac.commons.model.nodevalues.BasicNodeValue;
import it.isac.commons.model.sensors.SensorCounterMock;
import it.isac.commons.model.sensors.SensorGPS;
import it.isac.utils.impl.CMImplDesktop;
import it.isac.utils.impl.ComManagerFactory;

public class SimulatedClient {

	public static void main(String[] args) {
		// simple client for sensor testing
		Device dev = new Device((long) 2000);
		// Set manager implementation
		ComManagerFactory.setCMIstance(new CMImplDesktop());
		// Create sensor mock
		SensorCounterMock mock = new SensorCounterMock("mock0");
		mock.startCounting(1000);
		// create gps mock
		SensorGPS gpsMock = new SensorGPS("gpsMock");
		gpsMock.activateGPS(1000);
		// add sensors
		dev.addRealSensor(mock);
		dev.addRealSensor(gpsMock);
		// create starting value
		BasicNodeValue start = new BasicNodeValue("MockWriterField","DummyValue");
		// create and add mock field
		FieldFunctionMock mockFun = new FieldFunctionMock(start);
		dev.addField(mockFun);
		dev.start();
	}
}
