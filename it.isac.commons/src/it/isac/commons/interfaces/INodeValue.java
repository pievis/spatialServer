package it.isac.commons.interfaces;

public interface INodeValue { // aka field value
	//Values of the node are always strings
	public String getValue();
	public String getKey(); // every value had its id (key)
	public void setValue(String value);
	public void setKey(String key);
}
