package it.isac.server.utils.config;

import it.isac.db.search.SearchCriteria;

//Configuration POJO used for serialization/deserialization
public class ConfigPOJO {
	
	String name, description, versionName, loggerFilesDir;
	int portNumber;
	SearchCriteria searchCriteria;
//	String positionSystem;
	//properties for the DataBase
	DBConfig dbConfig;
	

	public ConfigPOJO() {
	}
	
//	public String getPositionSystem() {
//		return positionSystem;
//	}
//
//	public void setPositionSystem(String positionSystem) {
//		this.positionSystem = positionSystem;
//	}

	public String getLoggerFilesDir() {
		return loggerFilesDir;
	}

	public void setLoggerFilesDir(String loggerFilesDir) {
		this.loggerFilesDir = loggerFilesDir;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public SearchCriteria getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public DBConfig getDbConfig() {
		return dbConfig;
	}

	public void setDbConfig(DBConfig dbConfig) {
		this.dbConfig = dbConfig;
	}
	
	
}
