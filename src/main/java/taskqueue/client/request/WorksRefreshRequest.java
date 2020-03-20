package taskqueue.client.request;

import org.eclipse.jetty.http.HttpMethod;

import taskqueue.client.response.WorkListResponseMapper;

public class WorksRefreshRequest extends AbstractRequest {

	
	public WorksRefreshRequest() {
		this.responseMapper = new WorkListResponseMapper();
		this.localPath = "/works/";
	}
	
}
