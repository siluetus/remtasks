package taskqueue.client.request;

import java.io.UnsupportedEncodingException;

import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.BytesContentProvider;
import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.http.HttpMethod;
import org.json.simple.JSONAware;
import org.json.simple.parser.JSONParser;

import taskqueue.client.response.AbstractResponseManager;
import taskqueue.client.response.AbstractResponseMapper;
import taskqueue.json.ContainerFactory;
import taskqueue.json.JsonReply;


public abstract class AbstractRequest implements Runnable {
	
	protected String  protocolContentType = "application/json";
	protected Request httpRequest;
	protected HttpMethod httpMethod = HttpMethod.GET;
	protected JSONParser parser = new JSONParser();
	protected ContainerFactory jsoncontainerfactory = new ContainerFactory();
	protected AbstractResponseMapper responseMapper;
	protected String localPath = "/";
	protected AbstractResponseManager manager;
	
	
	public String getLocalPath() {
		return this.localPath;
	}
	
	public void run() {
		
		String content = "Error"; 
		try {
			ContentResponse response = httpRequest.send();
			String contentType = response.getHeaders().get("Content-Type");
			
			if(response.getStatus()!=200) {
				throw new Exception("Http status is "+String.valueOf(response.getStatus()));
			}
			
			if(!contentType.substring(0, this.protocolContentType.length()).equals(this.protocolContentType)) {
				throw new Exception("Invalid content type");
			}
			content = response.getContentAsString();
			JsonReply reply = (JsonReply) this.parser.parse(content,jsoncontainerfactory);
			responseMapper.responseUINormal(reply);

		} catch (Exception e) {
			responseMapper.responseUIError(e.getMessage(),null);
		}
		if(this.manager instanceof AbstractResponseManager) {
			manager.manage(responseMapper);
		}
		responseMapper.invokeProxyAction();
		
	}
	
	public void setHttpRequest(Request httpRequest) {
		this.httpRequest = httpRequest;
		this.httpRequest.method(this.httpMethod);
	}
	
	public AbstractResponseMapper getResponseMapper() {
		return this.responseMapper;
	}

	public AbstractResponseManager getManager() {
		return manager;
	}

	public void setManager(AbstractResponseManager manager) {
		this.manager = manager;
	}
	
	public void setJSONContent(JSONAware json) throws UnsupportedEncodingException {
		//this.httpRequest.header("Content-Type","application/json;charset=UTF8");
		this.httpRequest.content(new BytesContentProvider("application/json;charset=UTF8",json.toString().getBytes("UTF-8")));
	}
}
