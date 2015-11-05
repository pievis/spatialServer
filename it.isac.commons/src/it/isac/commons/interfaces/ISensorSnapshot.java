package it.isac.commons.interfaces;

/**
 * This is just the rappresentation of the values given by the sensor in a
 * specific time during computation
 * 
 * @author Pievis
 *
 */
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
