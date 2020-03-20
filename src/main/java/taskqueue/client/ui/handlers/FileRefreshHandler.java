package taskqueue.client.ui.handlers;

import taskqueue.client.request.AbstractRequest;
import taskqueue.client.request.CheckAuthRequest;
import taskqueue.client.request.FileRefreshRequest;
import taskqueue.client.ui.AbstractClientFrame;
import taskqueue.client.ui.proxy.AbstractUIProxy;
import taskqueue.client.ui.proxy.FileRefreshProxy;

public class FileRefreshHandler extends AbstractHandler {
	
	
	public FileRefreshHandler() {
		this.requestClazz = FileRefreshRequest.class;
	}
	
	@Override
	public AbstractUIProxy prepareProxy(AbstractClientFrame frame) {
		return new FileRefreshProxy(frame);
	}

	@Override
	protected void tuneRequest(AbstractRequest request) {
		
		
	}

}
