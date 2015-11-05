package it.isac.commons.model;

import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.IPosition;
import it.isac.commons.interfaces.ISensorSnapshot;

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
	List<ISensorSnapshot> sensors;
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
	public List<ISensorSnapshot> getSensors() {
		return sensors;
	}
	public void setSensors(List<ISensorSnapshot> sensors) {
		this.sensors = sensors;
	}
	
	public String toString(){
		String value = "";
		value += "[pos: " + position.toString() +
				", values: {";
		if(getValues() != null && getValues().size() > 0){
			value += getValues().get(0).toString();
			for(int i=1; i<getValues().size(); i++)
				value += ", "+getValues().get(i).toString();
		}
		value += "}, sensors: {";
		if(getSensors() != null && getSensors().size() > 0){
			value += getSensors().get(0).toString();
			for(int i=1; i<getSensors().size(); i++)
				value += ", "+getSensors().get(i).toString();
		}
		value += "}]";
		return value;
	}
}
