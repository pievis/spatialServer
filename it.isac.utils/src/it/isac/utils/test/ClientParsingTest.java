package it.isac.utils.test;

import it.isac.commons.interfaces.ISensorSnapshot;
import it.isac.commons.interfaces.resources.INodesResource;
import it.isac.commons.model.NodeState;
import it.isac.commons.model.XYPosition;
import it.isac.commons.model.sensors.SensorCounterMock;
import it.isac.commons.model.sensors.SensorGPS;
import it.isac.commons.requestresponse.SimpleResponse;
import it.isac.utils.impl.CMImplDesktop;
import it.isac.utils.impl.ComManagerFactory;

import java.io.IOException;
import java.util.ArrayList;

import org.restlet.resource.ClientResource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientParsingTest {
	
	final static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String args[]){
		test();
	}
	
	static void test(){
		String id = "gps1";
		XYPosition pos = new XYPosition(1,2);
		SensorGPS gps = new SensorGPS(id, pos);
		SensorCounterMock counter = new SensorCounterMock("mia_madre");
		counter.startCounting(500);
		NodeState ns = new NodeState();
		ns.setPosition(pos);
		ArrayList<ISensorSnapshot> sensors = new ArrayList<ISensorSnapshot>();
		
		sensors.add(counter.getValue());
		sensors.add(gps.getValue());
		ns.setSensors(sensors);
		
		//Parsing
		String json = "";
		try {
			json = mapper.writeValueAsString(ns);
		} catch (JsonProcessingException e) {
			log("Error");
			e.printStackTrace();
		}
		log(json);
		
		//Deserialize
		try {
			NodeState ns2 = mapper.readValue(json, NodeState.class);
			log(ns2.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Try send to server
		ClientResource service = new ClientResource("http://localhost:8111");
		INodesResource nodeRes = service.getChild("/net0/nodes/", INodesResource.class);
		SimpleResponse sr = nodeRes.addNode(ns);
		log(sr.getMessage());
		
		log("Testing the factory...");
		ComManagerFactory.setCMIstance(new CMImplDesktop());
		ComManagerFactory.getCMIstance().joinNetwork(ns);
	}
	
	static void log(String s){
		System.out.println(s);
	}
}
