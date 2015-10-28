package it.isac.db;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeState;

import java.util.Collection;
import java.util.List;

public class DataBase implements ISpatialDataBase {

	static ISpatialDataBase impl;
	
	public void SetImplementation(ISpatialDataBase impl){
		this.impl = impl;
	}
	
	public Node getNode(String id) {
		return impl.getNode(id);
	}

	public void updateNodeState(String id, NodeState state) {
		impl.updateNodeState(id, state);
	}

	public List<Node> getNeighbourhood(IPosition position, SearchCriteria searchCriteria) {
		return impl.getNeighbourhood(position, searchCriteria);
	}

	public boolean removeNode(String id) {
		return impl.removeNode(id);
	}
	
	//Singleton
	public static ISpatialDataBase getInstance(){
		if(impl == null)
			try {
				throw new Exception("Missing an implementation of DataBase");
			} catch (Exception e) {}
		return impl;
	}

	public Collection<Node> getAllNodes() {
		return impl.getAllNodes();
	}

//	public String getNewId() {
//		return impl.getNewId();
//	}
}
