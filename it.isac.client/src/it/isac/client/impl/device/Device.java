package it.isac.client.impl.device;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;

import it.isac.client.impl.managers.ComputationManager;
import it.isac.client.impl.managers.NetworkManager;
import it.isac.client.impl.managers.SensorManager;
import it.isac.client.interfaces.device.IDevice;
import it.isac.commons.interfaces.ISensor;

public class Device implements IDevice {

	// Device is both observer and observable
	ConcurrentHashMap<String, Observer> observers;
	SensorManager sensorMng;
	ComputationManager computatorMng;
	NetworkManager networkMng;
	int nextFunctionIndex = 0;
	Long freq; // scheduling frequency of the workers

	public Device(Long frequency) {
		this.freq = frequency;
		init();
	}

	private void init() {
		sensorMng = new SensorManager(freq);
		computatorMng = new ComputationManager(freq);
		networkMng = new NetworkManager(freq);
		// Device is observer for every function
		computatorMng.addObserver(this);
		// device is not a real observable: it just keep track of every observer
		observers = new ConcurrentHashMap<>();
	}

	@Override
	public void start() {
		// Tell to every manager to start their worker
		sensorMng.start();
		computatorMng.start();
		networkMng.start();
	}

	@Override
	public void stop() {
		// Tell to every manager to stop (pause) their worker
		sensorMng.stop();
		computatorMng.stop();
		networkMng.stop();
	}

	@Override
	public void dispose() {
		// Tell to every manager to dispose (halt) their worker
		sensorMng.dispose();
		computatorMng.dispose();
		networkMng.dispose();
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

	@Override
	public void addField(FieldCalculusFunction function, Observer fieldWatcher) {
		// Set function IDENTIFIER
		String functionId = "func" + nextFunctionIndex; // a more sophisticated
														// approach is required
		computatorMng.addField(function, functionId); // add field to be
														// computed
		observers.put(functionId, fieldWatcher); // add observer to notify the
													// viewer
		nextFunctionIndex++;
	}

	@Override
	public void update(Observable o, Object arg) {
		String fieldId = (String)arg; // use the field id to get the value
		
		String fieldValue = Domain.getIstance().getFieldValue(fieldId).getValue();
		// and to send the value to the appropriate observer
		observers.get(fieldId).update(null, fieldValue);
	}

}
