package it.isac.db;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeState;
import it.isac.db.search.SearchCriteria;

import java.util.Collection;
import java.util.List;

public interface ISpatialDataBase {
	
	/**
	 * 
	 * @param net the network name identifier
	 * @param id
	 * @return The node specified by the id.
	 */
	public Node getNode(String net, String id);
	
	/**
	 * Updates the state of the node.
	 * @param net the network name identifier
	 * @param id : Id of the node
	 * @param state : State of the node
	 */
	public void updateNodeState(String net, String id, NodeState state);
	
	/**
	 * Returns the list of nodes in the "spatial" area specified by the searchCriteria
	 * @param net the network name identifier 
	 * @param position
	 * @param searchCriteria
	 * @return list of the nearest node to the point position
	 */
	public List<Node> getNeighbourhood(String net, IPosition position, SearchCriteria searchCriteria);
	
	/**
	 * @param net the network name identifier
	 * @param id
	 * @return true if it's succefully removed
	 */
	public boolean removeNode(String net, String id);
	
	/**
	 * @param net the network name identifier
	 * @return all the nodes in the network
	 */
	public Collection<Node> getAllNodes(String net);
	
//	/**
//	 * **Implementation of this method must be thread safe.**
//	 * @return an unique identifier for a new node in the network.
//	 */
//	public String getNewId();
}
