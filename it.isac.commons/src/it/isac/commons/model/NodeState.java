package it.isac.commons.model;

import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.IPosition;
import it.isac.commons.interfaces.ISensor;

import java.util.List;
/**
 * This class shows the model of a NodeState
 * @author Pievis
 *
 */
public class NodeState {
	
	//String positionType;
	IPosition position;
	List<INodeValue> values;
	List<ISensor> sensors;
//	public String getPositionType() {
//		return positionType;
//	}
//	public void setPositionType(String positionType) {
//		this.positionType = positionType;
//	}
	public IPosition getPosition() {
		return position;
	}
	public void setPosition(IPosition position) {
		this.position = position;
	}
	public List<INodeValue> getValues() {
		return values;
	}
	public void setValues(List<INodeValue> values) {
		this.values = values;
	}
	public List<ISensor> getSensors() {
		return sensors;
	}
	public void setSensors(List<ISensor> sensors) {
		this.sensors = sensors;
	}
	
	
	
}
