package it.isac.server.utils;

/**
 * Attibutes used to identify URL patterns
 * @author Pievis
 *
 */
public class UrlAttributes {
	public final static String NODE_ID = "nodeId";
	public final static String NET_ID = "modelId";
	
	public static String getRelNodeUrl(){
		return "{"+NET_ID+"}/nodes/{"+NODE_ID+"}/";
	}
	
	public static String getRelNodeNbrUrl(){
		return getRelNodeUrl() + "nbr/";
	}
}
