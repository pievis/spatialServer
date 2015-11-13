package it.isac.commons.model;

import it.isac.commons.interfaces.IPosition;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, 
include = JsonTypeInfo.As.PROPERTY, 
//defaultImpl = LatLonPosition.class, //Uncomment only if necessary
property = "positionType" )
//add other position types here for automatic parsing
@JsonSubTypes({ @JsonSubTypes.Type(value = LatLonPosition.class,
						name = PositionType.LATLON),
	@JsonSubTypes.Type(value = XYPosition.class,
						name = PositionType.XY)					
						})
public abstract class Position implements IPosition {

	@Override
	public abstract String getPositionType();

	@Override
	public abstract void setPositionType(String positionType);

	@Override
	public String toString() {
		String value = "";
		try {
			ObjectMapper m = new ObjectMapper();
			value = m.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	//testing
	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException{
		Position p = new XYPosition(2, 4);
		Position p2 = new LatLonPosition(2,10);
		System.out.print(p.toString());
		System.out.print(p2.toString());
		///
		System.out.println(p2.toString());
		ObjectMapper m = new ObjectMapper();
		IPosition newPos = m.readValue(p2.toString(), Position.class);
		System.out.println(newPos.toString());
	}
}
