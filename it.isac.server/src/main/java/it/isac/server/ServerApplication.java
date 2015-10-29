package it.isac.server;

import it.isac.commons.model.Unit;
import it.isac.db.DataBase;
import it.isac.db.MemoryDB;
import it.isac.db.RangeSearch;
import it.isac.db.SearchCriteria;
import it.isac.server.resources.NeighboursServerResource;
import it.isac.server.resources.NodeServerResource;
import it.isac.server.resources.NodesServerResource;
import it.isac.server.resources.RootServerResource;
import it.isac.server.utils.ServerConfig;
import it.isac.server.utils.UrlAttributes;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 * This is a simple restlet application used for the spatial computing server.
 * @author Pievis
 */
public class ServerApplication extends Application {
	
	final static String BASE_URL = "/";
	
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
		//TODO add other resources if necessary
		return router; 
	}
	
	public ServerApplication(){
		setName("Spatial Computing Mediator");
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
