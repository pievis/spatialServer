package it.isac.commons.model;

import it.isac.commons.interfaces.ISensorSnapshot;

import java.util.ArrayList;
import java.util.Collection;

public class SensorSnapshotList extends ArrayList<ISensorSnapshot>{
	
	public SensorSnapshotList(Collection<ISensorSnapshot> sensors) {
		super(sensors);
	}
	
	public SensorSnapshotList(){}
	
}
