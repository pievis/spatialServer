package it.isac.client.impl.managers;

public abstract class DeviceJobWorker implements Runnable {
	
	public abstract void doJob();
	
	@Override
	public void run() {
		doJob();
	}
}
