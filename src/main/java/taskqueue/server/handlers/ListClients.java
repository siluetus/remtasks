package taskqueue.server.handlers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

import org.json.simple.JSONArray;

import taskqueue.server.Server;
import taskqueue.server.client.Client;


public class ListClients extends AbstractHandler {

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		Server server = this.getTaskQServer();
		
		Client client = server.getClientHeader(request);
		if(!(client instanceof Client) || !client.isAdmin()) {
			server.respondHTTP400(response);
			response.getWriter().println("Admin only");
			baseRequest.setHandled(true);
			return;			
		}
		
		JSONArray answer = new JSONArray();
		
		for(Enumeration<UUID> clientsID = server.getAllClients();
				clientsID.hasMoreElements();) {
				answer.add(clientsID.nextElement().toString());
		}
		
		server.respondJSON(answer, response);
		
		baseRequest.setHandled(true);
		
	}
	

}
