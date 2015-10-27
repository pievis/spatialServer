package it.isac.server.resources;

import it.isac.commons.interfaces.resources.INodeResource;
import it.isac.commons.model.Node;
import it.isac.commons.requestresponse.SimpleResponse;
import it.isac.db.DataBase;
import it.isac.db.ISpatialDataBase;
import it.isac.server.utils.UrlAttributes;

import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class NodeServerResource extends ServerResource implements INodeResource {

	String nodeId;
	ISpatialDataBase sb;
	
	@Override
	protected void doInit() throws ResourceException{
		//Get the nodeId from the url
		nodeId = getAttribute(UrlAttributes.NODE_ID);
		sb = DataBase.getInstance(); //get db instance
	}
	
	//GET
	public Node represent() {
		Node node = sb.getNode(nodeId);
		return node;
	}

	public SimpleResponse update(Node node) {
		SimpleResponse sr;
		try{
			sb.updateNodeState(node.getId(), node.getState());
			sr = new SimpleResponse(true, "Node updated");
		}catch(Exception e){
			e.printStackTrace();
			sr = new SimpleResponse(false, "Error " + e.getMessage());
		}
		return sr;
	}
	
	public Representation delete(){
		//TODO to implement node delete
		return null;
	}
	
}
