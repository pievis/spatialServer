package it.isac.server.utils;

/**
 * Attibutes used to identify URL patterns
 * @author Pievis
 *
 */
public class UrlAttributes {
	public final static String NODE_ID = "nodeId";
	public final static String NET_ID = "modelId";
	
	public static String getRelNodesUrl(){
		return "{"+NET_ID+"}/nodes/";
	}
	
	public static String getRelNodeUrl(){
		return getRelNodesUrl()+"{"+NODE_ID+"}/";
	}
	
	public static String getRelNodeNbrUrl(){
		return getRelNodeUrl() + "nbr/";
	}
}
