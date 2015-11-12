package it.isac.commons.model.sensors;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.interfaces.ISensor;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.XYPosition;

public class SensorGPS implements ISensor {
	
	long timeInterval;
	String id;
	Thread behaviour;
	IPosition position;
	
	public SensorGPS(String id) {
		this.id = id;
		// by default position is euclieand coord
		position = new XYPosition(0,0);
	}
	public SensorGPS(String id, IPosition pos) {
		this.id = id;
		//unless otherwise specified
		this.position = pos;
	}
	
	public void activateGPS(long waitSec) {
		// despite the name, what this sensor do is mocking a GPS
				behaviour = new Thread(new Runnable() {
					@Override
					public void run() {
						while (true) {
							// no changes
//							System.out.println("counter updated " + counter);
//							System.out.println(getValue().toString());
							try {
								Thread.sleep(timeInterval);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				});
	}

	@Override
	public ISensorSnapshot getValue() {
		return new BaseSensorSnapshot(id, getType(), position.toString());
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getType() {
		return SensorType.GPS;
	}

}
