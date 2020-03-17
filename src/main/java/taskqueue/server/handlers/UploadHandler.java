package taskqueue.server.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

import taskqueue.server.client.Client;
import taskqueue.server.manager.ClientFile;
import taskqueue.server.manager.ClientFolder;
import taskqueue.server.manager.FileManager;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class UploadHandler extends AbstractHandler {
	
	protected Logger logger;
	
	public UploadHandler() {
		super();
		logger = Log.getLogger(this.getClass());
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
			
		try {
			
			ClientFolder clientfolder = this.getServer().getBean(FileManager.class).getClientFolder(client);
			JSONArray answer = new JSONArray();
			JSONObject fileanswer;
			if(request.getMethod()=="PUT") {
				
				answer.add(this.putfile(clientfolder
						,request.getInputStream()
						,request.getHeader("Filename")));
			}
			
			if(request.getMethod()=="GET") {
				answer = (JSONArray)this.getfilelist(clientfolder);
			}
			
			this.respondJSON(answer, response);
			baseRequest.setHandled(true);
			return;			
			
		} catch (Exception e) {
			this.respondHTTP400(response, "Upload error");
			baseRequest.setHandled(true);
			logger.info(String.format(" File uploading error (%s) type %s", e.getMessage(),e.getClass().toString()));
			return;
		}
	}
	
	
	public JSONAware putfile(ClientFolder clientfolder,InputStream is, String newFileName) throws Exception {
		
		
		if(org.eclipse.jetty.util.StringUtil.isEmpty(newFileName)) {
			throw new Exception("No file name");
		}
		
		
		ClientFile clientfile = clientfolder.putFile(is, newFileName);
		
		JSONObject fileanswer = new JSONObject();
		
		fileanswer.put("FileID", clientfile.getUuid().toString());
		fileanswer.put("FileName",clientfile.getFileName());
		
		return fileanswer;
		
	}
	
	public JSONAware getfilelist(ClientFolder clientfolder) {
		JSONArray answer = new JSONArray();
		Enumeration<String> files = clientfolder.getClientFiles();
		
		while(files.hasMoreElements()) {
			ClientFile clientfile = clientfolder.getClientFile(files.nextElement());
			JSONObject fileanswer = new JSONObject();
			fileanswer.put("FileID", clientfile.getUuid().toString());
			fileanswer.put("FileName",clientfile.getFileName());
			answer.add(fileanswer);
		}
		
		return answer;
	}

}
