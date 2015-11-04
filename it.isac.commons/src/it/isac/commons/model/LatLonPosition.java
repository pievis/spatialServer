package it.isac.commons.model;

import it.isac.commons.interfaces.ILatLonPosition;

public class LatLonPosition implements ILatLonPosition {

	double lat, lon;
	
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
		return PositionType.LATLON;
	}
	
	public void setPositionType(String positionType){}
	
	//utils
	public static double distance(LatLonPosition p1, LatLonPosition p2, Unit unit){
		double theta = p1.getLon() - p2.getLon();
		double dist = Math.sin(deg2rad(p1.getLat())) * Math.sin(deg2rad(p2.getLat())) +
				Math.cos(deg2rad(p1.getLat())) * Math.cos(deg2rad(p2.getLat())) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == Unit.KM) {
			dist = dist * 1.609344;
		}
		if (unit == Unit.M) {
			dist = dist * 1.609344 * 1000;
		}
		return dist;
	}
	
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
