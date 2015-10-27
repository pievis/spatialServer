package it.isac.commons.model;

import it.isac.commons.interfaces.ILatLonPosition;

public class LatLonPosition implements ILatLonPosition {

	double lat, lon;
	String positionType;
	
	public LatLonPosition(){}
	
	public LatLonPosition(double lat, double lon){
		this.lat = lat;
		this.lon = lon;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	@Override
	public double getLat() {
		return lat;
	}

	@Override
	public double getLon() {
		return lon;
	}
	
	public String toString(){
		return "[lat:"+lat+",lon:"+lon+"]";
	}

	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	
	
}
