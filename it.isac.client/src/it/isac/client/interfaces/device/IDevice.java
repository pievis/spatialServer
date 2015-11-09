package it.isac.client.interfaces.device;

import it.isac.commons.interfaces.ISensor;

public interface IDevice {
	
	// Computation control methods
	public void start();
	public void stop();
	public void dispose();
	
	// Sensor manager control methods
	public void addRealSensor(ISensor sensor);
	public void addSimulatedSensor(ISensor sensor);
	
}
