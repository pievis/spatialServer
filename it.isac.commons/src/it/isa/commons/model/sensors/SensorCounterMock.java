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
	public void startCounting(long waitMilliSec) {
		this.timeInterval = waitMilliSec;
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (counter < MAX_VAL) {
					counter++;
//					System.out.println("counter updated " + counter);
//					System.out.println(getValue().toString());
					try {
						Thread.sleep(timeInterval);
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
	
	public static void main(String args[]){
		new SensorCounterMock("test").startCounting(500);
	}
}
