package it.isac.commons.model;

/**
 * This is the model of the node in the spatial computing framework.
 * @author Pievis
 *
 */
public class Node {

	String id;
	NodeState state;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public NodeState getState() {
		return state;
	}
	public void setState(NodeState state) {
		this.state = state;
	}
}
