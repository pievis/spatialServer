package it.isa.commons.model.sensors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.isac.commons.interfaces.ISensor;
import it.isac.commons.interfaces.ISensorSnapshot;

public class SensorCounterMock implements ISensor {

	final int MAX_VAL = 1000;
	int counter = 0;
	long timeInterval;
	String id;
	
	public SensorCounterMock(String id) {
		this.id = id;
	}

	@JsonIgnore
	public void startCounting(long waitSec) {
		this.timeInterval = waitSec;
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (counter < MAX_VAL) {
					counter++;
					try {
						Thread.sleep(timeInterval * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

	// ////

	@Override
	public ISensorSnapshot getValue() {
		return new BaseSensorSnapshot(id, getType(), counter+"");
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getType() {
		return SensorType.MOCK;
	}
}
