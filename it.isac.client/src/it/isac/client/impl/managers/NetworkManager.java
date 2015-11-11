package it.isac.client.impl.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.isac.client.impl.device.Domain;
import it.isac.client.interfaces.managers.INetworkManager;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeList;
import it.isac.commons.model.NodeState;
import it.isac.utils.impl.ComManagerFactory;
import it.isac.utils.impl.ComunicationManager;

public class NetworkManager extends AbstractManager implements INetworkManager {
	ComunicationManager manager; // proxy-like
	private final String joinerId = "Jonny";
	private final String nbrFetcherId = "nbrFetcher";
	private final String senderId = "stateSender";

	private String deviceId;

	public NetworkManager(Long initFreq) {
		super(initFreq);
		manager = ComManagerFactory.getCMIstance();
		init();
	}

	private void init() {
		// add network joiner worker
		workers.add(new NetworkJoinWorker(joinerId, this));
	}

	@Override
	public void updateValue(String id, Object value) {
		if (value != null) {
			if (id == joinerId && !((String) value).isEmpty()) {
				this.stop();
				workers.remove(0); // remove the joiner worker
				// save the deviceID in the network
				deviceId = (String) value;
				// start new workers
				workers.add(new NetworkNbrFetcherWorker(nbrFetcherId, this, deviceId));
				workers.add(new NetworkSenderWorker(senderId, this, deviceId));
				this.start();
			} else if (id == nbrFetcherId && !((NodeList) value).isEmpty()) {
				// Update the domain
				HashMap<String, NodeState> nbrTable = new HashMap<>();
				NodeList nbr = (NodeList) value;
				for (Node n : nbr) {
					// Build the neighbourhood table
					nbrTable.put(n.getId(), n.getState());
				}
				Domain.getIstance().updateAllNbr(nbrTable);
			}
			// else is the state sender and I don't have to do anything with the
			// value
		}
	}

	@Override
	public NodeState getCurrentState() {
		// create a suitable representation for the worker
		Domain dIstance = Domain.getIstance();
		NodeState currentState = new NodeState();
		// set current position
		currentState.setPosition(dIstance.getPosition());
		// set sensors value, getting them from the domain
		currentState.setSensors(new ArrayList<ISensorSnapshot>(dIstance.getAllSensorValue().values()));
		// set fields value, getting them from the domain
		currentState.setValues(new ArrayList<INodeValue>(dIstance.getAllFieldsValue().values()));
		return currentState;
	}

	/*
	 * @Override public void setNeighbourhood(NodeList neighbourhood) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

}