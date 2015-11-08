package it.isac.commons.interfaces;

import it.isac.commons.model.nodevalues.BasicNodeValue;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, 
include = JsonTypeInfo.As.PROPERTY, 
defaultImpl = BasicNodeValue.class, //Default conversion
property = "class") 
public interface INodeValue { // aka field value
	//Values of the node are always strings
	public String getValue();
	public String getKey(); // every value had its id (key)
	public void setValue(String value);
	public void setKey(String key);
}
