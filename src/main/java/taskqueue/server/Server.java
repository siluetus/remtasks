package taskqueue.server;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Enumeration;
import java.util.UUID;

import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.json.simple.JSONAware;

import simple.embedding.jetty.HelloWorld;
import org.eclipse.jetty.util.log.Log;

public class Server extends org.eclipse.jetty.server.Server {
	
	protected ClientCollection clientCollection;
	
	Server(){
		super();
		this.clientCollection = new ClientCollection();
	}
	 
	public void initializeContexts() {
		ContextHandlerCollection collection = new ContextHandlerCollection();
		ContextHandler context;
		
		context = new ContextHandler("/hello");
		context.setHandler(new HelloWorld("<h1> Hello world </h1>"));
		collection.addHandler(context);
		
		context = new ContextHandler("/register");
		context.setHandler(new taskqueue.server.handlers.RegisterClient());
		collection.addHandler(context);
		
		context = new ContextHandler("/listclients");
		context.setHandler(new taskqueue.server.handlers.ListClients());
		collection.addHandler(context);
		
		context = new ContextHandler("/checkauth");
		context.setHandler(new taskqueue.server.handlers.CheckAuth());
		collection.addHandler(context);
		
		// Creating admin
		String adminID = this.registerClient();
		Client admin = this.clientCollection.getClient(adminID);
		admin.flyUpToGod();
		Log.getLog().info("Admin id is "+admin.getId().toString());
		
		this.setHandler(collection);
	}
	
	public void initializeState() {
		
	}
	
	public String registerClient() {
		String newID = this.clientCollection.registerClient();
		return newID;
	}
	
	public Enumeration<UUID> getAllClients() {
		return this.clientCollection.getAllClients();
	}
	
	public void respondJSON(JSONAware answer, HttpServletResponse response) throws IOException {
	    // Declare response encoding and types
        response.setContentType("application/json; charset=utf-8");

        // Declare response status code
        response.setStatus(HttpServletResponse.SC_OK);
        
        response.getWriter().print(answer);
	}
	
	public void respondHTTP400(HttpServletResponse response) {
        // Declare response status code
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}
	
	public Client getClientHeader(HttpServletRequest request) {
		try {
			String clientID = request.getHeader("ClientID");
			Client client = this.clientCollection.getClient(clientID);
			return client;
		} catch(Exception e) {
			Log.getLog().info("Invalid client");
		}
		return null;
		
	}
	
}
