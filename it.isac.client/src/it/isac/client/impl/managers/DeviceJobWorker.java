package it.isac.client.impl.managers;

public abstract class DeviceJobWorker implements Runnable {
	
	AbstractManager mng;
	String id;
	Object val;
	
	public DeviceJobWorker(String id, AbstractManager manager) {
		this.id = id;
		this.mng = manager;
	}
	public abstract void doJob();
	
	@Override
	public void run() {
		doJob();
		mng.updateValue(id, val);
	}
}
