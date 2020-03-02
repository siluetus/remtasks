package taskqueue.server.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

import org.eclipse.jetty.server.Request;
import taskqueue.server.Server;

public class RegisterClient extends AbstractHandler{

	@Override
	public void handle(String target,
			Request baseRequest,
			HttpServletRequest request,
			HttpServletResponse response)
			throws IOException, ServletException {
		
		Server server = this.getTaskQServer();
		String clientId = server.registerClient();
		response.setContentType("application/json");
		JSONArray answer = new JSONArray();
		answer.add(clientId);
		server.respondJSON(answer, response);
		
		baseRequest.setHandled(true);
	}
	

	
	
}
