package taskqueue.client.request;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;

import taskqueue.client.response.CheckAuthResponseMapper;

public class CheckAuthRequest extends AbstractRequest {
	
	CheckAuthRequest(){
		this.responseMapper = new CheckAuthResponseMapper();
	}

	public String getLocalPath() {
		return "/checkauth";
	}
	
}
