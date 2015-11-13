package it.isac.client.interfaces.device;

import java.util.Observer;

import it.isac.client.impl.device.FieldCalculusFunction;
import it.isac.commons.interfaces.ISensor;

public interface IDevice extends Observer {
	
	// Computation control methods
	public void start();
	public void stop();
	public void dispose();
	
	// Sensor manager control methods
	public void addRealSensor(ISensor sensor);
	public void addSimulatedSensor(ISensor sensor);
	
	// Computation manager control methods
	public void addField(FieldCalculusFunction function, Observer fieldViewer);
	//public void addField(FieldCalculusFunction function);
}
