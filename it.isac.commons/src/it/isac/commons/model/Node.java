package it.isac.commons.model;

import java.util.ArrayList;

/**
 * This is the model of the node in the spatial computing framework.
 * @author Pievis
 *
 */
public class Node {

	String id;
	NodeState state;

	public Node(){}
	
	public Node(String id, NodeState state){
		this.id = id;
		this.state = state;
	}

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
