package it.isac.client.impl.managers;

import it.isac.commons.model.NodeState;
import it.isac.utils.impl.ComManagerFactory;

public class NetworkJoinWorker extends DeviceJobWorker {

	public NetworkJoinWorker(String id, AbstractManager manager) {
		super(id, manager);
	}

	@Override
	public void doJob() {
		System.out.println("--->Network join worker");
		NodeState state = ((NetworkManager)mng).getCurrentState();
		if(state.getPosition() != null) {
			String nodeId = ComManagerFactory.getCMIstance().joinNetwork(state);
			this.val = nodeId;
		}
	}

}
