package it.isac.commons.interfaces.resources;

import it.isac.commons.model.Node;
import it.isac.commons.model.NodeState;
import it.isac.commons.requestresponse.SimpleResponse;

import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface INodesResource {
	
	@Get
	public List<Node> represent();
	
	@Post
	public SimpleResponse addNode(NodeState nodeState);
}
