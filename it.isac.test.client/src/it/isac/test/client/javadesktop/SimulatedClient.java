package it.isac.test.client.javadesktop;

import it.isac.client.impl.device.Device;
import it.isac.client.impl.device.LatLonExecContext;
import it.isac.client.impl.device.XYExecContext;
import it.isac.commons.interfaces.IPosition;
import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.nodevalues.BasicNodeValue;
import it.isac.commons.model.sensors.SensorCounterMock;
import it.isac.commons.model.sensors.SensorGPS;
import it.isac.commons.model.sensors.SensorSource;
import it.isac.utils.impl.CMImplDesktop;
import it.isac.utils.impl.ComManagerFactory;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class SimulatedClient {

	final static int DEVICE_NUMBER = 10;
	final static double MAX_DISTANCE = 0.025;
	
	public static void main(String[] args) {
		//piazza del popolo
		Random random = new Random(34);
		LatLonPosition start = new LatLonPosition(44.137199, 12.241922);
//		for(int i = 0; i < DEVICE_NUMBER-1; i++){
//			double newLon, newLat;
//			newLon = start.getLon() + random.nextDouble() * MAX_DISTANCE;
//			newLat = start.getLat() + random.nextDouble() * MAX_DISTANCE;
//			LatLonPosition pos = new LatLonPosition(newLat, newLon);
//			new SimulatedClient(false, pos);
//			System.out.println("Started at position: " + pos.toString());
//		}
		new SimulatedClient(true, start);
		new SimulatedClient(false, new LatLonPosition(44.137560, 12.241320));
//		new SimulatedClient(false, new LatLonPosition(44.135996, 12.240365));
//		new SimulatedClient(false, new LatLonPosition(44.140027, 12.242564));
//		new SimulatedClient(false, new LatLonPosition(44.139623, 12.243427));
//		new SimulatedClient(false, new LatLonPosition(44.145154, 12.249463));
		
	}

	public SimulatedClient(boolean isSource, IPosition position) {
		// simple client for sensor testing
		Device dev = new Device((long) 2000);
		// Set manager implementation
		ComManagerFactory.setCMIstance(new CMImplDesktop());
		// Create sensor mock
//		SensorCounterMock mock = new SensorCounterMock("mock0");
//		mock.startCounting(1000);
		// create gps mock
		SensorGPS gpsMock = new SensorGPS("gpsMock", position);
//		gpsMock.activateGPS(1000);
		SensorSource source = new SensorSource("source");
		if (isSource) {
			source.setOn();
		}
		// add sensors
//		dev.addRealSensor(mock);
		dev.addRealSensor(gpsMock);
		dev.addRealSensor(source);
		// create starting value
//		BasicNodeValue start = new BasicNodeValue("MockCounterField", "1");
		BasicNodeValue gradStart = new BasicNodeValue("distGrad", "inf");
		// create and add mock field
//		FieldFunctionMock mockFun1 = new FieldFunctionMock(start);
		GradientFunction grad = new GradientFunction(gradStart);
		grad.setExecutionContext(new LatLonExecContext());
//		Observer mockObserver1 = new Observer() {
//
//			@Override
//			public void update(Observable o, Object arg) {
////				System.out.println("New value received from 1");
////				System.out.println((String) arg);
//			}
//		};
		Observer mockObserver2 = new Observer() {

			@Override
			public void update(Observable o, Object arg) {
//				System.out.print("Gradient Value: ");
//				System.out.println((String) arg);
			}
		};
//		dev.addField(mockFun1, mockObserver1);
		dev.addField(grad, mockObserver2);
		dev.start();
	}
}
