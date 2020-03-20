package taskqueue.server.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.eclipse.jetty.server.Request;

import taskqueue.server.works.Work;
import taskqueue.json.JsonReply;
import taskqueue.json.JsonReplyList;
import taskqueue.json.JsonReplyString;
import taskqueue.server.client.Client;
import taskqueue.server.manager.ClientFile;
import taskqueue.server.manager.FileManager;
import taskqueue.server.manager.WorksManager;
import taskqueue.server.works.ClientWorkByJar;

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
		FileManager fileManager = this.getServer().getBean(FileManager.class);
		JSONArray answer = new JSONArray();
		try {
			
			if(request.getMethod()=="POST") {

				JsonReply requestBody = this.readJSONrequest(request);  
				if(requestBody instanceof JsonReplyList) {
					Iterator<JsonReply> iterator = ((JsonReplyList)requestBody).iterator();
					while(iterator.hasNext()) {
						JsonReply rep = iterator.next();
						if(rep instanceof JsonReplyString) {
							UUID fileID = UUID.fromString(((JsonReplyString) rep).toString());
							ClientFile cf = fileManager.getFileById(fileID);
							answer.add(this.addWork(fileID, client, cf , worksManager));
						}
					}
				}
				 
			}
			
			
			if(request.getMethod()=="GET") {
				this.listWorks(client.getClientWorks(), worksManager,answer);
			}
		
			this.respondJSON(answer, response);
			baseRequest.setHandled(true);
			return;
			
		}catch(Exception e) {
			this.respondHTTP400(response, String.format("%s %s", e.getClass().toString(), e.getMessage()));
			baseRequest.setHandled(true);
			logger.info(String.format("Work creation error %s of  %s", e.getMessage(),e.getClass().toString()));
			return;
		}
		
		
	}
	
	
	public String addWork(UUID fileID, Client client, ClientFile cf, WorksManager worksManager) throws Exception {

		if(!(cf.getClientID().equals(client.getId()) || cf.isShared())) {
			throw new Exception(String.format("Acces is denied for user %s and file %s", fileID.toString(), client.getId().toString()));
		}
		
		ClientWorkByJar cw = worksManager.createWorkFromJar(cf);
		cw.setClientID(client.getId());
		worksManager.queueWork(cw, client);
		return cw.getUuid().toString();
	}
	
	
	public void listWorks(UUID[] workIds, WorksManager worksManager, List<JSONAware> result){

		for(int i=0;i<workIds.length;i++) {
			JSONObject jsonwork = new JSONObject();
			Work work = worksManager.getWork(workIds[i]);
			jsonwork.put("WorkID", work.getUuid().toString());
			if(work instanceof ClientWorkByJar) {
				ClientFile workfile = ((ClientWorkByJar) work).getFile();
				ClientFile outfile = ((ClientWorkByJar) work).getOut();
				jsonwork.put("WorkType","jar");
				if(workfile!=null) {
					JSONObject wfjson = new JSONObject();
					wfjson.put("FileID",workfile.getUuid().toString());
					wfjson.put("FileName",workfile.getFileName());
					wfjson.put("FileOwnerID",workfile.getClientID().toString());
					jsonwork.put("JarFile", wfjson);
				}
				
				if(outfile!=null) {
					JSONObject ofjson = new JSONObject();
					ofjson.put("FileID",outfile.getUuid().toString());
					ofjson.put("FileName",outfile.getFileName());
					jsonwork.put("OutFile", ofjson);

				}
				jsonwork.put("WorkState", work.getStateAsString());
			}
			result.add(jsonwork);
		}		
		
	}
	
	
	public JSONObject work2Json(ClientWorkByJar clientWork) {
		return null;
		
	}
	
	public void getClientWorks(UUID clientID) {
		
	}

}
