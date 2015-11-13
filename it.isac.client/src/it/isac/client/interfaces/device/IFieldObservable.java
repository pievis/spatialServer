package it.isac.client.interfaces.device;

import java.util.Observer;

public interface IFieldObservable {
	public void addObserver(Observer observer);
	public void notifyChange(String fieldId);
}
