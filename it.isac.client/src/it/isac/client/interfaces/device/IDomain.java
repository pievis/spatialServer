package it.isac.client.interfaces.device;

import java.util.HashMap;

import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.IPosition;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.NodeState;

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
	public NodeState getNbr(String key);
	public HashMap<String, NodeState> getAllNbr();
	public void updateNbr(String key, NodeState value);
	public void updateAllNbr(HashMap<String, NodeState> newNbr);
	
	// Position Modifier
	public IPosition getPosition();
	public void setPosition(IPosition newPosition);
	
	//NodeId
	public String getNodeId();
	public void setNodeId(String nodeId);
}
