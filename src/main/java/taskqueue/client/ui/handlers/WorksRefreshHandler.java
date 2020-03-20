package taskqueue.client.ui.handlers;

import taskqueue.client.request.AbstractRequest;
import taskqueue.client.response.WorkListResponseMapper;
import taskqueue.client.ui.AbstractClientFrame;
import taskqueue.client.ui.proxy.AbstractUIProxy;
import taskqueue.client.ui.proxy.WorksRefreshProxy;

public class WorksRefreshHandler extends AbstractHandler {
	
	public WorksRefreshHandler(){
		this.requestClazz = WorkListResponseMapper.class; 
	}

	@Override
	public AbstractUIProxy prepareProxy(AbstractClientFrame frame) {
		return new WorksRefreshProxy(frame);
	}

	@Override
	protected void tuneRequest(AbstractRequest request) {
		
	}

}
