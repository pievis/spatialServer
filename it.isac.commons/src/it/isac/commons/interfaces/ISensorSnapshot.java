package it.isac.commons.interfaces;

import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.PositionType;
import it.isac.commons.model.XYPosition;
import it.isac.commons.model.sensors.BaseSensorSnapshot;
import it.isac.commons.model.sensors.SensorType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This is just the rappresentation of the values given by the sensor in a
 * specific time during computation
 * 
 * @author Pievis
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, 
include = JsonTypeInfo.As.PROPERTY, 
defaultImpl = BaseSensorSnapshot.class, //Default conversion
property = "@class" )
//add subtypes if neccessary
//@JsonSubTypes({ @JsonSubTypes.Type(value = BaseSensorSnapshot.class,
//	name = SensorType.MOCK)})
public interface ISensorSnapshot {
	
	public String getSensorId();
	public void setSensorId(String sensorId);
	
	/**
	 * 
	 * @return the string reppresentation of the value/s of the sensor
	 */
	public String getValue();
	public void setValue(String value);

	/**
	 * 
	 * @return a string indentifying the type of this sensor
	 */
	public String getType();
	public void setType(String type);
}
