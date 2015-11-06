package it.isac.client.interfaces.managers;

public interface IManager {
	// Every Manager must be started, stopped, disposed
	public void start();
	public void stop();
	public void dispose();

}
