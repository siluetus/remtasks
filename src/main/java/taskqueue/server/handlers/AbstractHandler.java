package taskqueue.server.handlers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.json.simple.JSONAware;
import org.json.simple.parser.JSONParser;

import taskqueue.server.client.Client;
import taskqueue.server.manager.ClientManager;
import taskqueue.json.ContainerFactory;
import taskqueue.json.JsonReply;

public abstract class AbstractHandler extends org.eclipse.jetty.server.handler.AbstractHandler {
	
	protected int MAXPOSTSIZE =  314572800; // maximum 300 MB 
	
	protected JSONParser parser = new JSONParser();
	protected ContainerFactory jsoncontainerfactory = new ContainerFactory();
	
	protected taskqueue.server.Server getTaskQServer(){
		Server server = this.getServer();
		if(server instanceof taskqueue.server.Server) {
			return (taskqueue.server.Server) server;
		}
		return null;
	}
	
	
	protected Client getClient(HttpServletRequest request) {
		String clientID = request.getHeader("ClientID");
		if(org.eclipse.jetty.util.StringUtil.isEmpty(clientID)) {
			return null;
		}
		Client client = ((ClientManager) this.getServer().getBean(ClientManager.class)).getClient(clientID);
		return client;
	}
	
	public void respondHTTP400(HttpServletResponse response, String message) throws IOException {
        // Declare response status code
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.getWriter().println(message);
	}
	
	public void respondJSON(JSONAware answer, HttpServletResponse response) throws IOException {
	    // Declare response encoding and types
        response.setContentType("application/json; charset=utf-8");

        // Declare response status code
        response.setStatus(HttpServletResponse.SC_OK);
        
        response.getWriter().print(answer);
	}
	
	public JsonReply readJSONrequest(HttpServletRequest request ) throws Exception {
		
		if(!request.getContentType().contains("application/json")) {
			throw new Exception("Invalid Content Type");
		}
		
		int length = request.getContentLength();
		
		if(length>0) {
			if(length >this.MAXPOSTSIZE) {
				throw new Exception("Maximum POST size exceed");
			}
			
			return (JsonReply)parser.parse(request.getReader(), jsoncontainerfactory);
			
		}
		return null;

	}
}
