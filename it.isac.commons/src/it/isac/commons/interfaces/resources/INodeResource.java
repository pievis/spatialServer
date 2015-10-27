package it.isac.commons.interfaces.resources;

import it.isac.commons.model.Node;
import it.isac.commons.requestresponse.SimpleResponse;

import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

public interface INodeResource {

	@Get
	public Node represent();
	
	@Put
	public SimpleResponse update(Node node);
	
	@Delete
	public Representation delete();
}
