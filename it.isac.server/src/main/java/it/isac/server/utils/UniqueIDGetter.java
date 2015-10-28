package it.isac.server.utils;

import java.util.UUID;

/**
 * This class is used to generate an unique id for nodes.
 * @author Pievis
 */
public class UniqueIDGetter {
	
	/**
	 * Generates the unique id.
	 * Method is thread safe.
	 * @return string containing the unique identifier.
	 */
	public static synchronized String gen(){
		return UUID.randomUUID().toString();
	}
	
}
