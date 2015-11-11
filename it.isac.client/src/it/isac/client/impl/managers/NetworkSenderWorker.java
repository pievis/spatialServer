package it.isac.client.impl.managers;

import it.isac.commons.model.NodeState;
import it.isac.utils.impl.ComManagerFactory;

public class NetworkSenderWorker extends DeviceJobWorker {

	private String devId; // device id in the network
	
	public NetworkSenderWorker(String id, AbstractManager manager, String deviceId) {
		super(id, manager);
		this.devId = deviceId;
	}

	@Override
	public void doJob() {
		// get the current state
		NodeState currentState = ((NetworkManager)this.mng).getCurrentState();
		// send it accross the network
		ComManagerFactory.getCMIstance().sendState(devId, currentState);
		// no information returned to the manager
	}

}
