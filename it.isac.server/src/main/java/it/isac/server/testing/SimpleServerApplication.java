package it.isac.server.testing;

import it.isac.db.DataBase;
import it.isac.db.MemoryDB;
import it.isac.server.resources.NodeServerResource;
import it.isac.server.utils.UrlAttributes;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class SimpleServerApplication extends Application {

	public final static String BASE_URL = "http://localhost:8111/";
	
	public static void main(String args[]) throws Exception{
		//Setup db
		DataBase sb = new DataBase();
		sb.SetImplementation(new MemoryDB());
		//Setup Server
		Server server = new Server(Protocol.HTTP, 8111);
		server.setNext(new SimpleServerApplication());
		server.start();
		System.out.println("started...");
	}
	
	@Override
	public Restlet createInboundRoot(){
		Router router = new Router(getContext());
		//Node Resource
		router.attach(BASE_URL + UrlAttributes.getRelNodeUrl(),
				NodeServerResource.class);
		//TODO test other resources
		return router; 
	}
	
	public SimpleServerApplication(){
		setName("Simple Server Test 1");
		setAuthor("Pierluigi Montagna");
	}
}
