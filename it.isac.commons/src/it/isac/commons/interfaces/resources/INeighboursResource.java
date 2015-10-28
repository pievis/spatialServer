package it.isac.commons.interfaces.resources;

import it.isac.commons.model.NodeList;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public interface INeighboursResource {

	@Get
	public NodeList represent();
	
}
