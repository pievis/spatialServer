package it.isac.db;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.PositionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Simple DB that stores the information in memory.
 * @author Pievis
 *
 */
public class MemoryDB implements ISpatialDataBase {

	HashMap<String, Node> nodes;
	
	public MemoryDB(){
		nodes = new HashMap<String, Node>();
		//Add a node for testing
		NodeState state = new NodeState();
		state.setPositionType(PositionType.LATLON);
		LatLonPosition p = new LatLonPosition(0, 0);
		state.setPosition(p);
		state.setValues(new ArrayList());
		state.setSensors(new ArrayList());
		nodes.put("0", new Node("0", state));
	}
	
	public Node getNode(String id) {
		return nodes.get(id);
	}

	public void updateNodeState(String id, NodeState state) {
		nodes.put(id, new Node(id, state));
	}

	public List<Node> getNeighbourhood(IPosition position, String searchCriteria) {
		throw new UnsupportedOperationException();
	}

	public boolean removeNode(String id) {
		nodes.remove(id);
		return true;
	}

}
