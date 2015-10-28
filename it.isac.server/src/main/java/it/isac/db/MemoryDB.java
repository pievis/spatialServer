package it.isac.db;

import it.isac.commons.interfaces.IPosition;
import it.isac.commons.model.LatLonPosition;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.PositionType;
import it.isac.commons.model.XYPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Simple DB that stores the information in memory.
 * @author Pievis
 *
 */
public class MemoryDB implements ISpatialDataBase {

	HashMap<String, Node> nodes;
	
	public MemoryDB(){
		nodes = new HashMap<String, Node>();
		//Add a node for testing
		//addNode0();
	}
	
	public Node getNode(String id) {
		return nodes.get(id);
	}

	public void updateNodeState(String id, NodeState state) {
		nodes.put(id, new Node(id, state));
	}

	public List<Node> getNeighbourhood(IPosition position, SearchCriteria searchCriteria) {
		
		if(searchCriteria instanceof RangeSearch){
			return rangeSearch(position, (RangeSearch) searchCriteria);
		}
		if(searchCriteria instanceof NearestNSearch){
			throw new UnsupportedOperationException();
		}
		return null;
	}

	public boolean removeNode(String id) {
		nodes.remove(id);
		return true;
	}

	public Collection<Node> getAllNodes() {
		return nodes.values();
	}
	
	//Search Methods
	List<Node> rangeSearch(IPosition position, RangeSearch rs){
		ArrayList<Node> nbr = new ArrayList<Node>();
		double meters = rs.getMeters();
		String positionType = PositionType.LATLON;
		if(position instanceof XYPosition)
			positionType = PositionType.XY;
		for(Node n : nodes.values()){
			IPosition np = n.getState().getPosition();
			String npt = np.getPositionType();
			if(npt.equals(positionType)){ //Coordinates should be of the same type
				if(npt.equals(PositionType.LATLON)){
					//Distance between geo coordinates
					double distance = LatLonPosition.distance((LatLonPosition) position,
							(LatLonPosition) np, rs.getUnit());
					if(distance < rs.getRange())
						nbr.add(n);
				}
				if(npt.equals(PositionType.XY)){
					//Distance between xy coordinates
					double distance = XYPosition.distance((XYPosition) position,
							(XYPosition) np);
					if(distance < meters)
						nbr.add(n);
				}
			}
			else{
				System.out.println("Ignored node " + n.getId());
			}
		}
		return nbr;
	}
	
	//For testing
	void addNode0(){
		NodeState state = new NodeState();
		LatLonPosition p = new LatLonPosition(0, 0);
		state.setPosition(p);
		state.setValues(new ArrayList());
		state.setSensors(new ArrayList());
		nodes.put("0", new Node("0", state));
	}
}
