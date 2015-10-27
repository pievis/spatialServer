package it.isac.commons.interfaces.resources;

import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface INodeResource {

	@Get
	public Representation represent();
	
	@Put
	public Representation update(Representation node);
	
	@Delete
	public Representation delete();
}
