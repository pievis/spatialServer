package it.isac.commons.interfaces;

public interface ISensor {
	public ISensorSnapshot getValue();
	public String getId();
	public String getType();
}
