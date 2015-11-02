package it.isac.server.testing;

import it.isac.commons.model.Unit;
import it.isac.db.DataBase;
import it.isac.db.MemoryDB;
import it.isac.db.search.RangeSearch;
import it.isac.db.search.SearchCriteria;
import it.isac.server.resources.NeighboursServerResource;
import it.isac.server.resources.NodeServerResource;
import it.isac.server.resources.NodesServerResource;
import it.isac.server.resources.RootServerResource;
import it.isac.server.utils.ServerConfig;
import it.isac.server.utils.UrlAttributes;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 * This is a simple restlet application used for the spatial computing server.
 * @author Pievis
 */
public class SimpleServerApplication extends Application {

	public final static String BASE_URL = "http://localhost:8111/";
	
	public static void main(String args[]) throws Exception{
		
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
		router.attach(BASE_URL, RootServerResource.class);
		router.attach(BASE_URL + UrlAttributes.getRelNodeUrl(),
				NodeServerResource.class);
		router.attach(BASE_URL + UrlAttributes.getRelNodesUrl(),
				NodesServerResource.class);
		router.attach(BASE_URL + UrlAttributes.getRelNodeNbrUrl(),
				NeighboursServerResource.class);
		//TODO test other resources
		return router; 
	}
	
	public SimpleServerApplication(){
		setName("Simple Server Test 1");
		setAuthor("Pierluigi Montagna");
		configure();
	}
	
	void configure(){
		//Setup db
		DataBase sb = new DataBase();
		sb.SetImplementation(new MemoryDB());
		//Set server parameters
		//Set search criteria for nbr
		SearchCriteria sc = new RangeSearch(10.0, Unit.M);
		ServerConfig.setSearchCriteria(sc);
	}
}
