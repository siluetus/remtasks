package taskqueue.client.ui.proxy;

import taskqueue.client.response.AbstractResponseMapper;

public abstract class AbstractUIProxy implements Runnable  {
	
	protected AbstractResponseMapper responseMapper;

	public AbstractResponseMapper getResponseMapper() {
		return responseMapper;
	}

	public void setResponseMapper(AbstractResponseMapper responseMapper) {
		this.responseMapper = responseMapper;
	}
}
