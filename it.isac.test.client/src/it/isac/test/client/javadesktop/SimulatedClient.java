package it.isac.test.client.javadesktop;

import java.util.Observable;
import java.util.Observer;

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
		BasicNodeValue start = new BasicNodeValue("MockCounterField", "1");
		// create and add mock field
		FieldFunctionMock mockFun1 = new FieldFunctionMock(start);
		FieldFunctionMock mockFun2 = new FieldFunctionMock(start);
		Observer mockObserver1 = new Observer() {

			@Override
			public void update(Observable o, Object arg) {
				System.out.println("New value received from 1");
				System.out.println((String) arg);
			}
		};
		Observer mockObserver2 = new Observer() {

			@Override
			public void update(Observable o, Object arg) {
				System.out.println("New value received from 2");
				System.out.println((String) arg);
			}
		};
		dev.addField(mockFun1, mockObserver1);
		dev.addField(mockFun2, mockObserver2);
		dev.start();
	}
}
