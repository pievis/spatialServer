package it.isac.commons.interfaces.resources;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public interface INeighboursResource {

	@Get
	public Representation represent();
	
}
