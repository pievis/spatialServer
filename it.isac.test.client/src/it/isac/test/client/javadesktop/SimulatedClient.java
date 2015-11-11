package it.isac.test.client.javadesktop;

import it.isac.client.impl.device.Device;
import it.isac.commons.model.nodevalues.BasicNodeValue;
import it.isac.commons.model.sensors.SensorCounterMock;

public class SimulatedClient {

	public static void main(String[] args) {
		// simple client for sensor testing
		Device dev = new Device((long) 1000);
		SensorCounterMock mock = new SensorCounterMock("mock0");
		mock.startCounting(500);
		SensorCounterMock mock2 = new SensorCounterMock("mock1");
		mock2.startCounting(700);
		dev.addRealSensor(mock);
		//dev.addRealSensor(mock2);
		BasicNodeValue start = new BasicNodeValue("MockWriterField","DummyValue");
		FieldFunctionMock mockFun = new FieldFunctionMock(start);
		//dev.addField(mockFun);
		dev.start();
	}

}
