package taskqueue.client.request;

import org.eclipse.jetty.http.HttpMethod;
import org.json.simple.JSONArray;

import taskqueue.client.ClientFile;
import taskqueue.client.response.UploadResponseMapper;

public class FileRunRequest extends AbstractRequest {

	
	protected ClientFile file2run;
	
	public FileRunRequest(){
		this.httpMethod = HttpMethod.POST;
		this.responseMapper = new UploadResponseMapper();
		this.localPath = "/works/";
	}

	
	public void run() {
		
		try {
			JSONArray body = new JSONArray();
			body.add(this.getFile2run().getFileId());
			this.setJSONContent(body);
			
		}catch(Exception e) {
			this.getResponseMapper().responseUIError("" + e.getClass().toString() +" " + e.getMessage(), null);
			this.getResponseMapper().invokeProxyAction();
			return;
		}
		
		super.run();
	}
	
	public ClientFile getFile2run() {
		return file2run;
	}

	public void setFile2run(ClientFile file2run) {
		this.file2run = file2run;
	}
	
	
	
}
