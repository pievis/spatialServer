package it.isac.client.interfaces.managers;

import it.isac.client.interfaces.sensors.ISensor;

public interface ISensorManager {
	
	public void addSensor(ISensor sensor);
	public void addSimulatedSensor(ISensor sensor);
}
