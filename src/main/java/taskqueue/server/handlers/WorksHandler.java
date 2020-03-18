package taskqueue.server.handlers;

import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.eclipse.jetty.server.Request;

import taskqueue.json.JsonReply;
import taskqueue.json.JsonReplyList;
import taskqueue.json.JsonReplyString;
import taskqueue.server.client.Client;
import taskqueue.server.manager.ClientFile;
import taskqueue.server.manager.FileManager;
import taskqueue.server.manager.WorksManager;
import taskqueue.server.works.ClientWork;

public class WorksHandler extends AbstractHandler {
	
	
	protected Logger logger;
	
	public WorksHandler() {
		logger =Log.getLogger(this.getClass().toString());
	}
	
	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Client client = this.getClient(request);

		if(!(client instanceof Client)) {
			this.respondHTTP400(response,"Unauthorized");
			baseRequest.setHandled(true);
			return;
		}
		
		WorksManager worksManager = this.getServer().getBean(WorksManager.class);
		FileManager fileManager = this.getBean(FileManager.class);
		JSONArray answer = new JSONArray();
		try {
			if(request.getMethod()=="POST") {

				JsonReply body = this.readJSONrequest(request);  
				if(body instanceof JsonReplyList) {
					Iterator<JsonReply> iterator = ((JsonReplyList)body).iterator();
					while(iterator.hasNext()) {
						JsonReply rep = iterator.next();
						if(rep instanceof JsonReplyString) {
							UUID fileID = UUID.fromString(((JsonReplyString) rep).toString());
							this.addWork(fileID, client, fileManager.getFileById(fileID), worksManager);
						}
					}
				}
				 
			}
		
		}catch(Exception e) {
			this.respondHTTP400(response, String.format("%s %s", e.getClass().toString(), e.getMessage()));
			baseRequest.setHandled(true);
			logger.info(String.format("Work creation error %s of class %s", e.getMessage(),e.getClass().toString()));
			return;
		}
		
		
	}
	
	
	public void addWork(UUID fileID, Client client, ClientFile cf, WorksManager worksManager) throws Exception {

		if(!(cf.getClientID().equals(client.getId()) || cf.isShared())) {
			throw new Exception(String.format("Acces is denied for user %s and file %s", fileID.toString(), client.getId().toString()));
		}
		
		ClientWork cw = worksManager.createWorkFromJar(cf);
		
		
	}
	
	
	public JSONObject work2Json(ClientWork clientWork) {
		return null;
		
	}
	
	public void getClientWorks(UUID clientID) {
		
	}

}
