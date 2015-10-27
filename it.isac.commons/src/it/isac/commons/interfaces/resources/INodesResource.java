package it.isac.commons.interfaces.resources;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public interface INodesResource {
	
	@Get
	public Representation represent();
	
	@Post
	public Representation addNode(Representation nodeState);
}
