package taskqueue.client;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;

import taskqueue.client.request.AbstractRequest;
import taskqueue.client.ui.AbstractClientFrame;
import taskqueue.client.ui.handlers.AbstractHandler;
import taskqueue.client.ui.handlers.SignUpHandler;

public class Client {
	
	protected RunClient runner;
	protected HttpClient httpClient;
	protected String clientID;
	protected String serverBaseURI;
	
	protected AbstractHandler signUpHandler; 
	
	
	public Client (RunClient runner) {
		this.runner = runner;
	}
	
	
	public void startHttpClient(String serverBaseURI) throws Exception {
		this.serverBaseURI = serverBaseURI;
		this.httpClient = new HttpClient();
		this.httpClient.setFollowRedirects(false);
		this.httpClient.start();
	}
	
	
	public void stopHttpClient() throws Exception {
		this.httpClient.stop();
	}
	
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
	public boolean isHttpReady() {
		return (this.httpClient instanceof HttpClient) && this.httpClient.isStarted(); 
	}
	
	public HttpClient getHttpClient() {
		return this.httpClient;
	}
	
	public void initHandlers(AbstractClientFrame clientFrame) {
		this.signUpHandler = new SignUpHandler();
		this.signUpHandler.setClient(this);
		clientFrame.getSignUpButton().addActionListener(this.signUpHandler);
		
	}
	
	
	public AbstractRequest createRequest(Class cls, String clientID) {
		try {
			if(!this.isHttpReady()) {
				throw new Exception("Http client does not work");
			}
				
			AbstractRequest newRequest = (AbstractRequest) cls.asSubclass(AbstractRequest.class).newInstance();
			Request httpRequest = this.httpClient.newRequest(this.serverBaseURI+newRequest.getLocalPath());
			newRequest.setHttpRequest(httpRequest);
			if(clientID!=null) {
				httpRequest.header("ClientID", clientID);
			}
			return newRequest;
		} catch (Exception e) {

		} 

		return null;
	}

	public RunClient getRunner() {
		return runner;
	}

	public String getClientID() {
		return clientID;
	}


	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
}
