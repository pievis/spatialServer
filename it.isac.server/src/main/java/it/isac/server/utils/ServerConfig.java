package it.isac.server.utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.isac.db.SearchCriteria;

/**
 * Class that stores the immutable configuration of the server.
 * Data of this class should be set only during server startup.
 * @author Pievis
 *
 */
public class ServerConfig {

	final static String CONFIG_FILE_NAME = "config.ini";
	
	static String name = "Spatial Computing server";
	static String versionName = "1.0v";
	static String description = "";
	
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
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		ObjectMapper mapper = new ObjectMapper();
		try {
			File f = new File(CONFIG_FILE_NAME);
			mapper.writeValue(f,config);
			System.out.print(CONFIG_FILE_NAME + " file created.\n"
					+ "At path: " + f.getAbsolutePath());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//For generating and loading test
	public static void main(String[] args){
		//genConfigFile();
		loadFromConfigFile();
		System.out.println(name + "\n" + description);
	}
	
	
}
