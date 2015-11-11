package it.isac.server.resources;

import java.util.logging.Level;
import java.util.logging.Logger;

import it.isac.commons.interfaces.resources.INodeResource;
import it.isac.commons.model.Node;
import it.isac.commons.requestresponse.SimpleResponse;
import it.isac.db.DataBase;
import it.isac.db.ISpatialDataBase;
import it.isac.server.utils.UrlAttributes;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Restlet resource that encapsulates the concept of node in the network.
 * @author Pievis
 */
public class NodeServerResource extends ServerResource implements INodeResource {

	
	Logger logger;
	String nodeId, netId;
	ISpatialDataBase sb;
	
	@Override
	protected void doInit() throws ResourceException{
		//Get the nodeId from the url
		nodeId = getAttribute(UrlAttributes.NODE_ID);
		netId = getAttribute(UrlAttributes.NET_ID);
		sb = DataBase.getInstance(); //get db instance
		logger = getLogger();
	}
	
	public Node represent() {
		Node node = sb.getNode(netId, nodeId);
		return node;
	}

	public SimpleResponse update(Node node) {
		SimpleResponse sr;
		try{
			//Position can't be null
			if(node.getState().getPosition() == null)
			{
				return new SimpleResponse(false, "Position can't be null");
			}
			
			sb.updateNodeState(netId, node.getId(), node.getState());
			sr = new SimpleResponse(true, "Node updated");
		}catch(Exception e){
			String err = "Error during the update of the node.\n" +
					"Uri: " + getReference().toString();
			logger.log(Level.SEVERE, err, e);
			sr = new SimpleResponse(false, "Error " + e.getMessage());
		}
		return sr;
	}
	
	public Representation delete(){
		//TODO to implement node delete
		return null;
	}
	
}
