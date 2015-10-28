package it.isac.commons.interfaces.resources;

import it.isac.commons.model.NodeList;
import it.isac.commons.model.NodeState;
import it.isac.commons.requestresponse.SimpleResponse;


import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface INodesResource {
	
	@Get
	public NodeList represent();
	
	@Post
	/**
	 * Post request to add a new node into the network.
	 * @param nodeState
	 * @return a SimpleResponse with the unique id for that node.
	 */
	public SimpleResponse addNode(NodeState nodeState);
}
