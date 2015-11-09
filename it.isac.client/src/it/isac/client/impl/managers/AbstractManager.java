package it.isac.client.impl.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import it.isac.client.interfaces.managers.IManager;

// AbstractManager: representing a generic Manager that control workers
public abstract class AbstractManager implements IManager {
	
	private ScheduledThreadPoolExecutor schedulerExecutorService;
	protected List<ScheduledFuture<?>> execHandlers;
	protected Long frequency;
	protected List<DeviceJobWorker> workers;
	
	protected AbstractManager(Long initFreq) {
		// init global fields
		this.frequency = initFreq;
		workers = new ArrayList<DeviceJobWorker>();
		execHandlers = new ArrayList<ScheduledFuture<?>>();
		schedulerExecutorService = new ScheduledThreadPoolExecutor(1);
	}
	
	protected void restart(Long scheduleFreq) {
		for(DeviceJobWorker worker : workers)
			execHandlers.add(schedulerExecutorService.scheduleAtFixedRate(worker, scheduleFreq, scheduleFreq, TimeUnit.MILLISECONDS));
	}
	
	@Override
	public void start() { // start the workers
		// other parameters could be added
		restart(frequency);
	}

	@Override
	public void stop() { //stop (pause) workers
		// other parameters could be added
		for(ScheduledFuture<?> handler : execHandlers)
			if(handler!=null)
				handler.cancel(false);
	}

	@Override
	public void dispose() {
		schedulerExecutorService.shutdown();
	}
	
	public abstract void updateValue(String id, Object value);

}
