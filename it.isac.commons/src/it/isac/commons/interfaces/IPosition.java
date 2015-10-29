package it.isac.commons.interfaces;

import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.PositionType;
import it.isac.commons.model.XYPosition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
public interface IPosition {
	@JsonIgnore
	public String getPositionType();
	public void setPositionType(String positionType);
	public String toString();
}
