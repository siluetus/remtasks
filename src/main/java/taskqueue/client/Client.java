package taskqueue.client;

import javax.swing.AbstractButton;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;

import taskqueue.client.request.AbstractRequest;
import taskqueue.client.response.AbstractResponseManager;
import taskqueue.client.response.SignedUpResponseManager;
import taskqueue.client.response.SignedUpResponseMapper;
import taskqueue.client.ui.AbstractClientFrame;
import taskqueue.client.ui.handlers.AbstractHandler;
import taskqueue.client.ui.handlers.SignInHandler;
import taskqueue.client.ui.handlers.SignUpHandler;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

public class Client {
	
	protected RunClient runner;
	protected HttpClient httpClient;
	protected String clientID;
	protected Boolean admin;
	protected String serverBaseURI;
	protected SignedUpResponseManager signedUpManager;
	
	protected AbstractHandler signUpHandler;
	protected AbstractHandler signInHandler; 	
	
	
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
		Logger log =Log.getLog();
		log.info("Stopping http client...");
		this.httpClient.stop();
		this.signedUpManager = null;
		this.signUpHandler = null;
		this.signInHandler = null;
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
		
		this.signedUpManager = new SignedUpResponseManager(this);
		this.signUpHandler = this.createUiHandler(SignUpHandler.class,clientFrame,clientFrame.getSignUpButton());
		this.signInHandler = this.createUiHandler(SignInHandler.class,clientFrame,clientFrame.getSignInButton());

	}
	
	
	public AbstractRequest createRequest(Class<?> cls, String clientID) {
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

	
	public AbstractHandler createUiHandler(Class<?> handlerClazz, AbstractClientFrame clientFrame, AbstractButton button) {
		try {
			AbstractHandler newHandler = (AbstractHandler) handlerClazz.newInstance();
			newHandler.setClient(this);
			newHandler.setFrame(clientFrame);
			button.addActionListener(newHandler);
			return newHandler;
		} catch (InstantiationException e) {
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			
		}
		
		return null;
	}



	public SignedUpResponseManager getSignedUpManager() {
		return signedUpManager;
	}


	public void setSignedUpManager(SignedUpResponseManager signedUpManager) {
		this.signedUpManager = signedUpManager;
	}


	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
}
