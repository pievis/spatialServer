package it.isac.server.resources;

import java.util.Collection;
import it.isac.commons.interfaces.resources.INodesResource;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeList;
import it.isac.commons.model.NodeState;
import it.isac.commons.requestresponse.IdClass;
import it.isac.commons.requestresponse.SimpleResponse;
import it.isac.db.DataBase;
import it.isac.db.ISpatialDataBase;
import it.isac.server.utils.UniqueIDGetter;

import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
/**
 * Rest resources that encapsulates the concept of /nodes as a collection of nodes in the network.
 * @author Pievis
 */
public class NodesServerResource extends ServerResource implements INodesResource {

	ISpatialDataBase sb;
	
	@Override
	protected void doInit() throws ResourceException{
		sb = DataBase.getInstance(); //get db instance
	}
	
	public NodeList represent() {
		Collection<Node> nodes = sb.getAllNodes();
		return new NodeList(nodes);
	}

	public SimpleResponse addNode(NodeState nodeState) {
		//Generate an unique ID
		String id = UniqueIDGetter.gen();
		
		//add the node to the db
		sb.updateNodeState(id, nodeState);
		
		//return the generated id to the client
		SimpleResponse sr = new SimpleResponse(true, "New node added to the network");
		sr.setData(new IdClass(id));
		return sr;
	}

}
