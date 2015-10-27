package it.isac.commons.interfaces;

import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.PositionType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, 
			include = JsonTypeInfo.As.EXTERNAL_PROPERTY, 
			//defaultImpl = LatLonPosition.class, //Uncomment only if necessary
			property = "positionType" )
//TODO add other position types
@JsonSubTypes({ @JsonSubTypes.Type(value = LatLonPosition.class,
									name = PositionType.LATLON)})
public interface IPosition {

	public String getPositionType();
	public String toString();
}
