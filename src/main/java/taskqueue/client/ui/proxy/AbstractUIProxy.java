package taskqueue.client.ui.proxy;

import taskqueue.client.response.AbstractResponseMapper;
import taskqueue.client.ui.AbstractClientFrame;

public abstract class AbstractUIProxy implements Runnable  {
	
	protected AbstractResponseMapper responseMapper;
	protected AbstractClientFrame frame;
	
	
	public AbstractResponseMapper getResponseMapper() {
		return responseMapper;
	}

	public void setResponseMapper(AbstractResponseMapper responseMapper) {
		this.responseMapper = responseMapper;
	}
	
	public AbstractClientFrame getFrame() {
		return this.frame;
	}
	
}
