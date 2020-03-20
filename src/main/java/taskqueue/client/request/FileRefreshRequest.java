package taskqueue.client.request;

import org.eclipse.jetty.http.HttpMethod;

import taskqueue.client.response.FileListResponseMapper;

public class FileRefreshRequest extends AbstractRequest {
	
	
	public FileRefreshRequest() {
		this.responseMapper = new FileListResponseMapper();
		this.localPath = "/upload/";
	}

}
