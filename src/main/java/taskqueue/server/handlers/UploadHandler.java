package taskqueue.server.handlers;

import java.io.IOException;

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
		
		ClientFolder clientfolder = this.getServer().getBean(FileManager.class).getClientFolder(client);
		
		
		String newFileName = request.getHeader("Filename");
		
		if(org.eclipse.jetty.util.StringUtil.isEmpty(newFileName)) {
			this.respondHTTP400(response, "No filename present");
			baseRequest.setHandled(true);
			return;
		}
		
		
		try {
			ClientFile clientfile = clientfolder.putFile(request.getInputStream(), newFileName);
			
			JSONObject answer = new JSONObject();
			
			answer.put("FileID", clientfile.getUuid().toString());
			answer.put("FileName",clientfile.getFileName());
			
			this.respondJSON(answer, response);
			baseRequest.setHandled(true);
			return;
		} catch (Exception e) {
			logger.info(String.format(" File uploading error (%s)", e.getMessage()));
		}
	}

}
