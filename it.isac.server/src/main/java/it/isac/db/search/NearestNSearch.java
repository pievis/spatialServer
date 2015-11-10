package it.isac.db.search;

import it.isac.commons.model.Unit;

public class NearestNSearch extends SearchCriteria {
	int number;
	double maxDistance;
	Unit maxDistanceUnit;
	
	/**
	 *	 
	 * @return the max distance for the query
	 */
	public double getMaxDistance() {
		return maxDistance;
	}

	/**
	 * 
	 * @param maxDistance
	 */
	public void setMaxDistance(double maxDistance) {
		this.maxDistance = maxDistance;
	}

	/**
	 * 
	 * @return Unit used for the maxDistance
	 */
	public Unit getMaxDistanceUnit() {
		return maxDistanceUnit;
	}

	/**
	 * 
	 * @param maxDistanceUnit
	 */
	public void setMaxDistanceUnit(Unit maxDistanceUnit) {
		this.maxDistanceUnit = maxDistanceUnit;
	}

	public NearestNSearch(){}
	
	/**
	 * Get the number of nearest node to retrieve in the search.
	 * @return
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Set the number of nearest nodes to retrieve in the search.
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}
}
