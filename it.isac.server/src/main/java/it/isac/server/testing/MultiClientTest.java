package it.isac.server.testing;

import it.isa.commons.model.sensors.SensorCounterMock;
import it.isac.commons.interfaces.INodeValue;
import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.interfaces.resources.INeighboursResource;
import it.isac.commons.interfaces.resources.INodeResource;
import it.isac.commons.model.Node;
import it.isac.commons.model.NodeList;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.XYPosition;
import it.isac.commons.requestresponse.SimpleResponse;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
/**
 * Test with N clients
 * @author Pievis
 */
public class MultiClientTest {

	final static Logger LOGGER = Logger.getAnonymousLogger();
	final static String NET_NAME = "net0";
	
	public static void main(String args[]){
		XYPosition[] ps = {
				new XYPosition(5.0f, 15.0f),
				//new XYPosition(2.0f, 15.0f),
				//new XYPosition(1005.0f, 15.0f),
				new XYPosition(2005.0f, 1005.0f)
		};
		XYPosition end = new XYPosition(0.0f,0.0f);
		MultiClientTest x = new MultiClientTest();
		for(int i = 0; i < ps.length; i++){
			SClient c = (x).new SClient("client" + i, ps[i], end, i);
			c.startUpdating();
		}
	}
	
	class SClient extends Node {
		
		XYPosition end;
		XYPosition diff;
		double fetchWait = 500;
		long updateWait = 500;
		boolean ending; 
		double speed = 0.1;
		ClientResource service;
		
		public SClient(String name, XYPosition start, XYPosition end, long sensorTime){
			
			setId(name);
			this.end = end;
			diff = new XYPosition((end.getX()-start.getX())*speed, 
					(end.getY()-start.getY())*speed);
			service = new ClientResource("http://localhost:8111");
			//eventually join the network, not really necessary
			
			NodeState nodeState = new NodeState();
			nodeState.setPosition(start);
			nodeState.setSensors(new ArrayList<ISensorSnapshot>());
			nodeState.setValues(new ArrayList<INodeValue>());
			setState(nodeState);
			SensorCounterMock scm = new SensorCounterMock();
			scm.startCounting(sensorTime);
			ArrayList<ISensorSnapshot> sensors = new ArrayList<ISensorSnapshot>();
			sensors.add(scm);
			nodeState.setSensors(sensors);
			log("created");
		}
		
		 
		void move(){
			//Move
			XYPosition current =(XYPosition) this.getState().getPosition();
			current.setX(current.getX() + diff.getX());
			current.setY(current.getY() + diff.getY());
			log("Moved to: " + current.toString());
			getState().setPosition(current);
			//Check if at destination
			ending = XYPosition.distance(current, end) < 1.5 ? true : false; 
		}
		
		public void startUpdating(){
			Thread uThread = new Thread(new UpdateRunnable(this));
			uThread.start();
			log("update started");
		}
		
		void log(String str){
			LOGGER.info(getId() + "] " + str);
		}
		
		//
		private class UpdateRunnable implements Runnable{
			Node node;
			public UpdateRunnable(Node node) {
				this.node = node;
			}
			public void run() {
				while(!ending){
					//Move
					move();
					//Send position to server
					INodeResource nodeRes = service.getChild("/"+NET_NAME+"/nodes/"+getId()+"/", 
							INodeResource.class);
					//log(node.getId() + " " + node.getState().toString());
					SimpleResponse sr = nodeRes.update(node);
					log(sr.getMessage());
					//Get neighbourhood
					INeighboursResource nbrRes = service.getChild("/"+NET_NAME+"/nodes/" + getId() + "/nbr/",
							INeighboursResource.class);
					NodeList nodes = nbrRes.represent();
					for(Node n : nodes){
						log("NBR: \t" + n.getId());
						log("\t\t" + n.getState().toString());
					}
					//wait
					try {
						Thread.sleep(updateWait);
					} catch (InterruptedException e) {}
				}
			}
		}
	}
}
