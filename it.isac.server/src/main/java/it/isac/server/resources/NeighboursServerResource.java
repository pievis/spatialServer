package it.isac.server.resources;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.interfaces.resources.INeighboursResource;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeList;
import it.isac.db.DataBase;
import it.isac.db.ISpatialDataBase;
import it.isac.db.SearchCriteria;
import it.isac.server.utils.ServerConfig;
import it.isac.server.utils.UrlAttributes;

import java.util.List;

import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class NeighboursServerResource extends ServerResource implements INeighboursResource {

	ISpatialDataBase sb;
	SearchCriteria sc;
	String nodeId;
	
	@Override
	protected void doInit() throws ResourceException{
		sb = DataBase.getInstance(); //get db instance
		sc = ServerConfig.getSearchCriteria(); //get def search criteria
		nodeId = getAttribute(UrlAttributes.NODE_ID);
	}
	
	public NodeList represent() {
		//Get position of the node
		Node n = sb.getNode(nodeId);
		IPosition pos = n.getState().getPosition();
		List<Node> nbr = sb.getNeighbourhood(pos, sc);
		return new NodeList(nbr);
	}

}
