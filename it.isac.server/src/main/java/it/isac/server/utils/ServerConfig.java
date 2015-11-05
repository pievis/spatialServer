package it.isac.server.utils;

import it.isac.commons.model.PositionType;
import it.isac.db.DataBase;
import it.isac.db.MemoryDB;
import it.isac.db.RedisDB;
import it.isac.db.search.SearchCriteria;
import it.isac.server.utils.config.ConfigPOJO;
import it.isac.server.utils.config.DBConfig;
import it.isac.server.utils.config.DBMemoryConfig;
import it.isac.server.utils.config.DBRedisConfig;

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
	//static String positionSystem=PositionType.LATLON;
	
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
	 * Load Server configuration properties from the config.ini file.
	 * The class also setup the server database.
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
			//positionSystem = config.getPositionSystem();
			LOGGER.info("Loaded configuration from file\n-----");
			LOGGER.info(name + " " + description + " " + versionName);
			LOGGER.info("port number: " + portNumber);
			LOGGER.info("log folder: " + loggerFilesFolder);
			DBConfig dbconfig = config.getDbConfig();
			if(dbconfig != null){
				//Here to add new db cases if necessary
				if(dbconfig instanceof DBRedisConfig){
					LOGGER.info("Db type: Redis");
					DBRedisConfig rcf = (DBRedisConfig) dbconfig;
					String url = rcf.getUrl();
					String port = rcf.getPort();
					RedisDB db = new RedisDB(url, port);
					db.setExpirationSeconds(rcf.getExpirationSeconds());
					db.setUseExpiration(rcf.isUseExpiration());
					new DataBase(db);
					LOGGER.info("url: " + url);
					LOGGER.info("port: " + port);
					if(rcf.isUseExpiration())
						LOGGER.info("expiration time (s): " + rcf.getExpirationSeconds());
				}else/* if(dbconfig instanceof DBMemoryConfig)*/{
					//base case
					LOGGER.info("Db type: Memory");
					new DataBase(new MemoryDB());
				}
			}else{
				LOGGER.info("Db type: Memory");
				new DataBase(new MemoryDB());
				LOGGER.log(Level.WARNING, "No db configuration found in config.ini");
			}
			LOGGER.info("-----");
		} catch (Exception e){
			LOGGER.log(Level.WARNING, "Error during config.ini parsing", e);
			e.printStackTrace();
		}
	}
	
	static void genConfigFile(){
		ConfigPOJO config = new ConfigPOJO();
		config.setDescription(description);
		config.setName(name);
		config.setPortNumber(portNumber);
		config.setSearchCriteria(getSearchCriteria());
		config.setVersionName(versionName);
		config.setLoggerFilesDir(loggerFilesFolder);
		//Redis Config def
//		DBRedisConfig dbconfig = new DBRedisConfig();
//		dbconfig.setPort("6379");
//		dbconfig.setUrl("redis://192.168.56.101");
//		dbconfig.setUseExpiration(true);
//		dbconfig.setExpirationSeconds(60);
		DBMemoryConfig dbconfig = new DBMemoryConfig();
		config.setDbConfig(dbconfig);
		//config.positionSystem = positionSystem;
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
		//genConfigFile();
		loadFromConfigFile();
		//System.out.println(name + "\n" + description);
	}
	
	
}
