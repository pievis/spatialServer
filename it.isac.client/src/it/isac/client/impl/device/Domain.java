package it.isac.client.impl.device;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import it.isac.client.interfaces.device.IDomain;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.IPosition;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.XYPosition;

// This class represent the current state of the domain. 
// It is also a (concurrent) unified access point
public class Domain implements IDomain {
	private static Domain dominio;

	private ConcurrentHashMap<String, INodeValue> fieldsValues;
	private ConcurrentHashMap<String, NodeState> nbrValues;
	private ConcurrentHashMap<String, ISensorSnapshot> sensorsValues;
	//private IPosition position = new XYPosition(0, 0);
	private IPosition position = null;

	// Singleton
	public static Domain getIstance() {
		if (dominio == null)
			dominio = new Domain();
		return dominio;
	}

	// constructor
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
		return new HashMap<>(fieldsValues);
	}

	@Override
	public void updateFieldValue(String key, INodeValue value) {
		if (fieldsValues.containsKey(key))
			fieldsValues.replace(key, value); // update
		else
			fieldsValues.put(key, value); // create
	}

	// Sensor Modifier
	@Override
	public ISensorSnapshot getSensorValue(String key) {
		return sensorsValues.get(key);
	}

	@Override
	public HashMap<String, ISensorSnapshot> getAllSensorValue() {
		return new HashMap<>(sensorsValues);
	}

	@Override
	public void updateSensorValue(String key, ISensorSnapshot value) {
		if (sensorsValues.containsKey(key))
			sensorsValues.replace(key, value); // update
		else
			sensorsValues.put(key, value); // create
	}

	// Neighbor Modifier
	@Override
	public NodeState getNbr(String key) {
		return nbrValues.get(key);
	}

	@Override
	public HashMap<String, NodeState> getAllNbr() {
		return new HashMap<>(nbrValues); 
	}

	@Override
	public void updateNbr(String key, NodeState value) {
		if (nbrValues.containsKey(key))
			nbrValues.replace(key, value); // update
		else
			nbrValues.put(key, value); // create
	}

	@Override
	public void updateAllNbr(HashMap<String, NodeState> newNbr) {
		nbrValues = new ConcurrentHashMap<>(newNbr); 
	}

	// Position Modifiers
	@Override
	public IPosition getPosition() {
		return position;
	}

	@Override
	public void setPosition(IPosition newPosition) {
		this.position = newPosition;
	}
}
