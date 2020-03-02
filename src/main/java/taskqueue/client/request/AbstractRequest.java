package taskqueue.client.request;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.http.HttpMethod;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

import taskqueue.client.response.AbstractResponseMapper;
import taskqueue.client.response.json.ContainerFactory;


public abstract class AbstractRequest implements Runnable {
	
	protected String  protocolContentType = "application/json";
	protected Request httpRequest;
	protected HttpMethod httpMethod = HttpMethod.GET;
	protected JSONParser parser = new JSONParser();
	protected ContainerFactory jsoncontainerfactory = new ContainerFactory();
	protected AbstractResponseMapper responseMapper;
	
	
	public abstract String getLocalPath();
	
	public void run() {
		
		try {
			ContentResponse response = httpRequest.send();
			String contentType = response.getHeaders().get("Content-Type");
			
			if(!contentType.substring(0, this.protocolContentType.length()).equals(this.protocolContentType)) {
				throw new Exception("Invalid content type");
			}
			this.parser.parse(response.getContentAsString(),jsoncontainerfactory);
			

		} catch (Exception e) {
		}
		
}	
	public void setHttpRequest(Request httpRequest) {
		this.httpRequest = httpRequest;
		this.httpRequest.method(this.httpMethod);
	}
	
	public AbstractResponseMapper getResponseMapper() {
		return this.responseMapper;
	}
}
