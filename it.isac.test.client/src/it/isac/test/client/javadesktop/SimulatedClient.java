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
		ComManagerFactory.setCMIstance(new CMImplDesktop());
		SensorCounterMock mock = new SensorCounterMock("mock0");
		mock.startCounting(1000);
		SensorCounterMock mock2 = new SensorCounterMock("mock1");
		mock2.startCounting(700);
		SensorGPS gpsMock = new SensorGPS("gpsMock");
		//dev.addRealSensor(mock);
		//dev.addRealSensor(mock2);
		//dev.addRealSensor(gpsMock);
		BasicNodeValue start = new BasicNodeValue("MockWriterField","DummyValue");
		FieldFunctionMock mockFun = new FieldFunctionMock(start);
		//dev.addField(mockFun);
		dev.start();
	}
}
