package it.isac.server;

import it.isac.server.testing.SimpleServerApplication;
import it.isac.server.utils.ServerConfig;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class SpatialServerComponent extends Component {

	/**
	 * Starts the HTTP server
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		new SpatialServerComponent().start();
	}
	
	public SpatialServerComponent(){
		setup();
	}
	
	/**
	 * Setup the Http server
	 */
	void setup(){
		setupInfo();
		//add http server connector
		int port = ServerConfig.getPortNumber();
		getServers().add(Protocol.HTTP, port);
		//add application to the server
		getDefaultHost().attachDefault(new ServerApplication());
	}
	
	void setupInfo(){
		String name = "Spatial Computing Server 1.0";
		String description = "This is the ..";
		setName(name);
		setDescription(description);
		setAuthor("Pierluigi Montagna");
	}
	
}
