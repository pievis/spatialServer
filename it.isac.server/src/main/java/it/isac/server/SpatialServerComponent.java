package it.isac.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import it.isac.server.utils.ServerConfig;

import org.restlet.Component;
import org.restlet.data.Protocol;

import com.fasterxml.jackson.databind.Module.SetupContext;

public class SpatialServerComponent extends Component {

	final static Logger LOGGER = Logger.getLogger(SpatialServerComponent.class.getName());
	
	/**
	 * Starts the HTTP server
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		//if any args, then load then use that configuration file,
		if(args != null && args.length > 0){
			ServerConfig.CONFIG_FILE_NAME = args[0];
			LOGGER.config("Using config file: " + ServerConfig.CONFIG_FILE_NAME);
		}
		//otherwise use default config.ini in project folder
		setupLoggerProperties();
		new SpatialServerComponent().start();
		LOGGER.info("Spatial computing server started");
	}
	
	public SpatialServerComponent(){
		configure();
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
	
	void configure(){
		//Load Server configuration from file
			//Which now include db server configuration
		ServerConfig.loadFromConfigFile();
	}
	
	void setupInfo(){
		String name = ServerConfig.getName() + " "
				+ ServerConfig.getVersionName();
		String description = ServerConfig.getDescription();
		setName(name);
		setDescription(description);
		setAuthor("Pierluigi Montagna");
	}
	
	/**
	 * Tells the file configuration for the JVM logger.
	 * logging.properties file has to be in the execution folder.
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	static void setupLoggerProperties(){
		System.setProperty("java.util.logging.config.file",
		        "logging.properties");
		try {
			LogManager.getLogManager().readConfiguration();
			LOGGER.info("Logger has been configured");
		} catch (SecurityException e) {
			LOGGER.warning("A security exception has risen. Did you check the logging.properties access rights?");
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.warning("Currently impossible to read the file logging.properties.");
			e.printStackTrace();
		}
	}
	
}
