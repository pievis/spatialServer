package it.isac.server.utils;

import it.isac.db.SearchCriteria;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class that stores the immutable configuration of the server.
 * Data of this class should be set only during server startup.
 * @author Pievis
 *
 */
public class ServerConfig {

	final static String CONFIG_FILE_NAME = "config.ini";
	final static Logger LOGGER = Logger.getLogger(ServerConfig.class.getName());
	
	static String name = "Spatial Computing server";
	static String versionName = "1.0v";
	static String description = "";
	static String loggerFilesFolder="./logs/";
	
	/**
	 * Port number that the server listen to
	 */
	static int portNumber = 8111;
	
	/**
	 * Criteria that is used to compute the neighbourhood of a specific point in space.
	 * For example it could be a simple range or a more complex geometric area.
	 */
	static SearchCriteria searchCriteria;
	
	//
	public static void setSearchCriteria(SearchCriteria sc){
		searchCriteria = sc;
	} 
	
	//
	public static SearchCriteria getSearchCriteria(){
		if(searchCriteria == null)
			searchCriteria = SearchCriteria.getDefault();
		return searchCriteria;
	}

	/**
	 * Get the port number for the application server
	 * @return
	 */
	public static int getPortNumber() {
		return portNumber;
	}
	

	public static String getLoggerFilesFolder() {
		return loggerFilesFolder;
	}
	

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		ServerConfig.name = name;
	}

	public static String getVersionName() {
		return versionName;
	}

	public static void setVersionName(String versionName) {
		ServerConfig.versionName = versionName;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		ServerConfig.description = description;
	}
	
	//
	// Config file generation and loading
	//
	

	/**
	 * Load Server configuration properties from the config.ini file
	 */
	public static void loadFromConfigFile(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			ConfigPOJO config = mapper.readValue(new File(CONFIG_FILE_NAME), ConfigPOJO.class);
			name = config.getName();
			description = config.getDescription();
			versionName = config.getVersionName();
			searchCriteria = config.getSearchCriteria();
			portNumber = config.getPortNumber();
			loggerFilesFolder = config.getLoggerFilesDir();
		} catch (Exception e){
			LOGGER.log(Level.WARNING, "Error during config.ini parsing", e);
			e.printStackTrace();
		}
	}
	
	static void genConfigFile(){
		ConfigPOJO config = new ConfigPOJO();
		config.description = description;
		config.name = name;
		config.portNumber = portNumber;
		config.searchCriteria = getSearchCriteria();
		config.versionName = versionName;
		config.loggerFilesDir = loggerFilesFolder;
		ObjectMapper mapper = new ObjectMapper();
		try {
			File f = new File(CONFIG_FILE_NAME);
			mapper.writeValue(f,config);
			System.out.print(CONFIG_FILE_NAME + " file created.\n"
					+ "At path: " + f.getAbsolutePath());
			generateLoggerDir();
		} catch (JsonProcessingException e) {
			LOGGER.log(Level.WARNING, "Error durig config.ini creation", e);
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "Error durig config.ini creation", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Ensures the directory for the logger files exists.
	 */
	public static void generateLoggerDir(){
		File dir = new File(loggerFilesFolder);
		if(!dir.exists())
			dir.mkdirs();
	}
	
	//For generating and loading test
	public static void main(String[] args){
		genConfigFile();
		loadFromConfigFile();
		System.out.println(name + "\n" + description);
	}
	
	
}
