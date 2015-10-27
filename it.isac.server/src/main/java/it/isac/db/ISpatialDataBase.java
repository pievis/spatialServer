package it.isac.db;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeState;

import java.util.List;

public interface ISpatialDataBase {
	
	/**
	 * 
	 * @param id
	 * @return The node specified by the id.
	 */
	public Node getNode(String id);
	
	/**
	 * Updates the state of the node.
	 * @param id : Id of the node
	 * @param state : State of the node
	 */
	public void updateNodeState(String id, NodeState state);
	
	/**
	 * Returns the list of nodes in the "spatial" area specified by the searchCriteria 
	 * @param position
	 * @param searchCriteria
	 * @return list of the nearest node to the point position
	 */
	public List<Node> getNeighbourhood(IPosition position, String searchCriteria);
	
	/**
	 * 
	 * @param id
	 * @return true if it's succefully removed
	 */
	public boolean removeNode(String id);
	
}
