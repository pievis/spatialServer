package it.isac.server.resources;

import it.isac.server.utils.ServerConfig;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * This class is just a display that returns a presentation message 
 * @author Pievis
 */
public class RootServerResource extends ServerResource {

	@Get("txt")
	public String represent(){
		String message = "Spatial Computing ReSTlet Server\nVersion: " + ServerConfig.getVersionName();
		message += "\n" + ServerConfig.getDescription();
		return message;
	} 
}
