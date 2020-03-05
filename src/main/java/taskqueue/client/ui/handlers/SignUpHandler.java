package taskqueue.client.ui.handlers;

import java.awt.event.ActionListener;


import taskqueue.client.request.AbstractRequest;
import taskqueue.client.request.RegisterRequest;
import taskqueue.client.ui.AbstractClientFrame;
import taskqueue.client.ui.proxy.AbstractUIProxy;
import taskqueue.client.ui.proxy.SignedUpProxy;

public class SignUpHandler extends AbstractHandler implements ActionListener {
	
	///rotected Class<RegisterRequest> requestClazz = RegisterRequest.class;
	
	public SignUpHandler() {
		this.requestClazz = RegisterRequest.class;
	}
	@Override
	public AbstractUIProxy prepareProxy(AbstractClientFrame frame) {
		return new SignedUpProxy(frame);
	}

	@Override
	protected void tuneRequest(AbstractRequest request) {
		request.setManager(client.getSignedUpManager());
	}



	
	
	
	

}
