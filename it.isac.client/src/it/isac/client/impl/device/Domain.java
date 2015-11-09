package it.isac.client.impl.device;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import it.isac.client.interfaces.device.IDomain;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.Node;

// This class represent the current state of the domain. 
// It is also a (concurrent) unified access point
public class Domain implements IDomain {
	private static Domain dominio;
	
	private ConcurrentHashMap<String, INodeValue> fieldsValues;
	private ConcurrentHashMap<String, Node> nbrValues;
	private ConcurrentHashMap<String, ISensorSnapshot> sensorsValues;
	
	// Singleton
	public static Domain getIstance() {
		if(dominio == null)
			dominio = new Domain();
		return dominio;
	}
	
	//constructor
	private Domain() {
		fieldsValues = new ConcurrentHashMap<>();
		nbrValues = new ConcurrentHashMap<>();
		sensorsValues = new ConcurrentHashMap<>();
	}
	
	// Field Modifier
	@Override
	public INodeValue getFieldValue(String key) {
		return fieldsValues.get(key);
	}
	@Override
	public HashMap<String, INodeValue> getAllFieldsValue() {
		return new HashMap<>(fieldsValues); // TODO test
	}
	@Override
	public void updateFieldValue(String key, INodeValue value) {
		if(fieldsValues.containsKey(key))
			fieldsValues.replace(key, value); //update
		else
			fieldsValues.put(key, value); //create
	}
	
	// Sensor Modifier
	@Override
	public ISensorSnapshot getSensorValue(String key) {
		return sensorsValues.get(key);
	}
	@Override
	public HashMap<String, ISensorSnapshot> getAllSensorVelue() {
		return new HashMap<>(sensorsValues); // TODO test
	}
	@Override
	public void updateSensorValue(String key, ISensorSnapshot value) {
		if(sensorsValues.containsKey(key))
			sensorsValues.replace(key, value); //update
		else
			sensorsValues.put(key, value); //create
		// Just for test
		System.out.println(key + ": " + value.getSensorId() + "_" + value.getValue());
	}

	// Neighbor Modifier
	@Override
	public Node getNbr(String key) {
		return nbrValues.get(key);
	}
	@Override
	public HashMap<String, Node> getAllNbr() {
		return new HashMap<>(nbrValues); // TODO test
	}
	@Override
	public void updateNbr(String key, Node value) {
		if(nbrValues.containsKey(key))
			nbrValues.replace(key, value); //update
		else
			nbrValues.put(key, value); //create
	}
}
