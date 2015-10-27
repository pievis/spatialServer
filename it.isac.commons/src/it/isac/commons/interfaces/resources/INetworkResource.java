package it.isac.commons.interfaces.resources;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public interface INetworkResource {

	@Get
	public Representation represent();
	
}
