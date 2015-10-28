package it.isac.server.utils;

import it.isac.db.SearchCriteria;

/**
 * Class that stores the immutable configuration of the server.
 * Data of this class should be set only during server startup.
 * @author Pievis
 *
 */
public class ServerConfig {

	static SearchCriteria searchCriteria;
	
	//
	public static void setSearchCriteria(SearchCriteria sc){
		searchCriteria = sc;
	} 
	
	//
	public static SearchCriteria getSearchCriteria(){return searchCriteria;}
	
}
