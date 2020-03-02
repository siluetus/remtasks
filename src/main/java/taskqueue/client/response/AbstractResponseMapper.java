package taskqueue.client.response;

import org.json.simple.JSONAware;

import taskqueue.client.response.json.JSonReply;
import taskqueue.client.ui.proxy.AbstractUIProxy;

public abstract class AbstractResponseMapper {

	protected AbstractUIProxy proxy;
	
	public abstract void responseUINormal(JSonReply json);
	
	
	public void invokeProxyAction() {
		javax.swing.SwingUtilities.invokeLater(this.proxy);
	}
	
	public void setUIProxy(AbstractUIProxy proxy) {
		this.proxy = proxy;
		
	}
}
