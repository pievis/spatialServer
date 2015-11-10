package it.isac.client.impl.managers;

import java.util.List;

import it.isac.client.impl.device.Domain;
import it.isac.client.interfaces.managers.INetworkManager;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeList;
import it.isac.commons.model.NodeState;
import it.isac.utils.impl.ComManagerFactory;
import it.isac.utils.impl.ComunicationManager;

public class NetworkManager extends AbstractManager implements INetworkManager {
	ComunicationManager manager; // proxy-like
	private final String joinerId = "Jonny";
	private final String nbrFetcherId = "nbrFetcher";

	public NetworkManager(Long initFreq) {
		super(initFreq);
		manager = ComManagerFactory.getCMIstance();
		init();
	}

	private void init() {
		// add three workers
		workers.add(new NetworkJoinWorker(joinerId, this));
	}

	@Override
	public void updateValue(String id, Object value) {
		if (id == joinerId && (boolean)value) {
			workers.remove(0); // remove the joiner worker
			// TODO tell the deviceID in the network to someone
		}
		else if(id == nbrFetcherId) {
			// TODO Update the domain
			// Domain.getIstance().updateNbr(key, value);
		}
		// else is the state sender and I don't have to do anything with the value
	}

	@Override
	public NodeState getCurrentState() {

		return null;
	}

	@Override
	public void setNeighbourhood(NodeList neighbourhood) {
		// TODO Auto-generated method stub

	}

}
