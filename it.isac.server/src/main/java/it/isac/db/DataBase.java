package it.isac.db;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeState;
import it.isac.db.search.SearchCriteria;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class DataBase implements ISpatialDataBase {

	static ISpatialDataBase impl;

	public DataBase(ISpatialDataBase impl) {
		this.impl = impl;
	}

	public DataBase() {
	}

	public void SetImplementation(ISpatialDataBase impl) {
		this.impl = impl;
	}

	public Node getNode(String net, String id) {
		return impl.getNode(net, id);
	}

	public void updateNodeState(String net, String id, NodeState state) {
		impl.updateNodeState(net, id, state);
	}

	public List<Node> getNeighbourhood(String net, IPosition position,
			SearchCriteria searchCriteria) {
		return impl.getNeighbourhood(net, position, searchCriteria);
	}

	public boolean removeNode(String net, String id) {
		return impl.removeNode(net, id);
	}

	// Singleton
	public static ISpatialDataBase getInstance() {
		if (impl == null)
			try {
				throw new Exception("Missing an implementation of DataBase");
			} catch (Exception e) {
			}
		return impl;
	}

	public Collection<Node> getAllNodes(String net) {
		return impl.getAllNodes(net);
	}

	// public String getNewId() {
	// return impl.getNewId();
	// }
}
