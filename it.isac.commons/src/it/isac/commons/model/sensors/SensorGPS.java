package it.isac.commons.model.sensors;

import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.interfaces.ISensor;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.PositionType;
import it.isac.commons.model.XYPosition;

public class SensorGPS implements ISensor {

	String id;
	Thread behaviour;
	IPosition position;
	ScheduledThreadPoolExecutor exec;

	public SensorGPS(String id) {
		this.id = id;
		// by default position is euclieand coord
		position = new XYPosition(0, 0);
		init();
	}

	public SensorGPS(String id, IPosition pos) {
		this.id = id;
		// unless otherwise specified
		this.position = pos;
		init();
	}

	private void init() {
		exec = new ScheduledThreadPoolExecutor(1);
		// despite the name, what this sensor do is mocking a GPS
		behaviour = new Thread(new Runnable() {
			@Override
			public void run() {
				// random walk
				Random rnd = new Random();
				switch (position.getPositionType()) {
				case PositionType.XY:
					((XYPosition) position).setX(rnd.nextDouble() * 1.5);
					((XYPosition) position).setY(rnd.nextDouble() * 1.5);
					break;
				case PositionType.LATLON:
					((LatLonPosition) position).setLat(rnd.nextDouble() * 1.5);
					((LatLonPosition) position).setLon(rnd.nextDouble() * 1.5);
					System.out.print("+");
					break;
				}
			}
		});
	}
// Control Methods
	public void activateGPS(long waitSec) {
		exec.scheduleAtFixedRate(behaviour, waitSec, waitSec, TimeUnit.MILLISECONDS);
	}

	public void stopGPS() {
		exec.shutdown();
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
