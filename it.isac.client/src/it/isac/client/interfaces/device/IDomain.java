package it.isac.client.interfaces.device;

import java.util.HashMap;

import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensor;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.Node;

public interface IDomain {
	// Field modifier
	public INodeValue getFieldValue(String key);
	public HashMap<String, INodeValue> getAllFieldsValue();
	public void updateFieldValue(String key, INodeValue value);
	
	// Sensor Modifier
	public ISensorSnapshot getSensorValue(String key);
	public HashMap<String, ISensorSnapshot> getAllSensorValue();
	public void updateSensorValue(String key, ISensorSnapshot value);
	
	// Neighbor Modifier
	public Node getNbr(String key);
	public HashMap<String, Node> getAllNbr();
	public void updateNbr(String key, Node value);
}
