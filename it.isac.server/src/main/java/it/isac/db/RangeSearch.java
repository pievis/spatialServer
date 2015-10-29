package it.isac.db;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.isac.commons.model.Unit;

public class RangeSearch extends SearchCriteria {

	Unit unit;
	double range;
	
	public RangeSearch(){}
	
	public RangeSearch(double range, Unit unit){
		this.range = range;
		this.unit = unit;
	}

	/**
	 * Get the unit of the search for the range
	 * @return
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * Set the unit of the search for the range
	 * @param unit
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	/**
	 * 
	 * @return the circular range of the search in units
	 */
	public double getRange() {
		return range;
	}

	/**
	 * Set the circular range of the search in units
	 * @param range
	 */
	public void setRange(double range) {
		this.range = range;
	}
	
	@JsonIgnore
	public double getMeters(){
		if(unit == Unit.M)
			return range;
		else
			return range / 1000;
	}
	
	@JsonIgnore
	public double getKms(){
		if(unit == Unit.KM)
			return range;
		else
			return range * 1000;
	}
}
