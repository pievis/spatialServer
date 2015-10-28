package it.isac.commons.interfaces;

import com.fasterxml.jackson.annotation.JsonTypeInfo;


/**
 * Specifies a generic object sent and received by restlets.
 * @author Pievis
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="type")
public interface IData {
	public String getType();
	public void setType(String type);
}
