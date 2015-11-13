package it.isac.server.resources;

import it.isac.commons.interfaces.resources.INetworkResource;

import java.io.File;

import org.restlet.data.Disposition;
import org.restlet.data.MediaType;
import org.restlet.representation.FileRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class NetworkServerResource extends ServerResource implements
		INetworkResource {

	final static String FILE_PATH = "./res/index.html";
	
	@Get("text/html")
	public Representation represent() {
		File file = new File(FILE_PATH);
		FileRepresentation rep = new FileRepresentation(file,
				MediaType.TEXT_HTML);
		return rep;
		
	}

}
