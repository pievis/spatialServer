package it.isac.server;

import it.isac.db.DataBase;
import it.isac.db.MemoryDB;
import it.isac.server.resources.NeighboursServerResource;
import it.isac.server.resources.NodeServerResource;
import it.isac.server.resources.NodesServerResource;
import it.isac.server.resources.RootServerResource;
import it.isac.server.utils.ServerConfig;
import it.isac.server.utils.UrlAttributes;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * This is a simple restlet application used for the spatial computing server.
 * @author Pievis
 */
public class ServerApplication extends Application {
	
	final static String BASE_URL = "/";
	final static Logger LOGGER = Logger.getLogger(ServerApplication.class.getName());
	
	@Override
	public Restlet createInboundRoot(){
		Router router = new Router(getContext());
		//Node Resource
		router.attach(BASE_URL, RootServerResource.class);
		router.attach(BASE_URL + UrlAttributes.getRelNodeUrl(),
				NodeServerResource.class);
		router.attach(BASE_URL + UrlAttributes.getRelNodesUrl(),
				NodesServerResource.class);
		router.attach(BASE_URL + UrlAttributes.getRelNodeNbrUrl(),
				NeighboursServerResource.class);
		//TODO add other resources if necessary
		return router; 
	}
	
	public ServerApplication(){
		setName("Spatial Computing Mediator");
		setAuthor("Pierluigi Montagna");
		configure();
	}
	
	void configure(){
		//Setup db
		DataBase sb = new DataBase();
		sb.SetImplementation(new MemoryDB());
		//Set server parameters
		//Load Server configuration from file
		ServerConfig.loadFromConfigFile();
		try {
			configureLogger();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error during logger configuration.", e);
		}
	}
	
	void configureLogger() throws SecurityException, IOException{
		ServerConfig.generateLoggerDir();
//		String dir = ServerConfig.getLoggerFilesFolder();
//		LOGGER.info("Setted logger dir: " + dir);
//		//Set a global file handler for the logger
//		FileHandler fileHandler = new FileHandler(dir + "logfile%g.log", 5242880, 5, true);
//		fileHandler.setLevel(Level.ALL);
//		Logger.getGlobal().addHandler(fileHandler);
	}
}
