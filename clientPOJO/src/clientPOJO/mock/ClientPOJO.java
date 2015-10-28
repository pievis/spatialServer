package clientPOJO.mock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.isac.commons.model.NodeState;
import it.isac.commons.model.XYPosition;
import it.isac.utils.impl.ComunicationManager;
import it.isac.utils.impl.CMImplDesktop;
import it.isac.utils.impl.ComManagerFactory;

public class ClientPOJO {

	public static void main(String[] args) {

		ComManagerFactory.setCMIstance(new CMImplDesktop());
		ComunicationManager manager = ComManagerFactory.getCMIstance();
		NodeState state = new NodeState();
		state.setPosition(new XYPosition(0.0, 0.0));
		manager.joinNetwork(state);

		ExecutorService exec = Executors.newFixedThreadPool(5);
//		exec.execute(new Runnable() {
//
//			@Override
//			public void run() {
//				ComunicationManager cm = ComManagerFactory.getCMIstance();
//				while (true) {
//					cm.fetchNeighbour("node0");
//				}
//			}
//		});
//		exec.execute(new Runnable() {
//
//			@Override
//			public void run() {
//				ComunicationManager cm = ComManagerFactory.getCMIstance();
//				while (true) {
//					cm.sendState("node0", new NodeState());
//				}
//			}
//		});
	}
}
