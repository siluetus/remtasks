package taskqueue.client.ui.proxy;

import taskqueue.client.ui.AbstractClientFrame;
import taskqueue.client.response.SignedUpResponseMapper;

public class SignedUpProxy extends AbstractUIProxy {
	
	protected AbstractClientFrame frame;
	
	public SignedUpProxy(AbstractClientFrame frame) {
		this.frame = frame;
	}
	
	public void run() {
		SignedUpResponseMapper mapper = (SignedUpResponseMapper)this.getResponseMapper();
		this.frame.getSigninTextField().setText(mapper.getClientID());
	}

}
