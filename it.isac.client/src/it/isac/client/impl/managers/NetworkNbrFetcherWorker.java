package it.isac.client.impl.managers;

import it.isac.commons.model.NodeList;
import it.isac.utils.impl.ComManagerFactory;

public class NetworkNbrFetcherWorker extends DeviceJobWorker {

	String devId; // device id in the network
	
	public NetworkNbrFetcherWorker(String id, AbstractManager manager, String deviceId) {
		super(id, manager);
		this.devId = deviceId;
	}

	@Override
	public void doJob() {
		//System.out.println("---> Network worker: fetcher");
		// just fetch the neighbourhood
		NodeList nbr = ComManagerFactory.getCMIstance().fetchNeighbour(devId);
		this.val = nbr; // and give it to the manager
	}

}
