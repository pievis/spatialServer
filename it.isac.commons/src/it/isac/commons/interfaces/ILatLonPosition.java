package it.isac.commons.interfaces;

public interface ILatLonPosition extends IPosition {

	public void setLat(double lat);
	public void setLon(double lon);
	public double getLat();
	public double getLon();
	
}
