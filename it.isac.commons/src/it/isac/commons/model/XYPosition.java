package it.isac.commons.model;

import it.isac.commons.interfaces.IPosition;

public class XYPosition extends Position implements IPosition{

	double x,y;
	
	public XYPosition(){}
	
	public XYPosition(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String getPositionType() {
		return PositionType.XY;
	}
	
	public void setPositionType(String positionType){}
	
	@Override
	public String toString() {
		return "[x:" + x + ",y:" + y + "]";
	}
	
	//Util methods
	public static double distance(XYPosition p1, XYPosition p2){
		double val = Math.sqrt(
	            (p1.getX() - p2.getX()) *  (p1.getX() - p2.getX()) + 
	            (p1.getY() - p2.getY()) *  (p1.getY() - p2.getY())
	        );
		return val;
	}
}
