package taskqueue.client.response;

import taskqueue.client.response.json.JsonReply;
import taskqueue.client.ui.proxy.AbstractUIProxy;

public abstract class AbstractResponseMapper {

	protected AbstractUIProxy proxy;
	protected AbstractUIProxy errorProxy;
	protected String error;
	
	public abstract void responseUINormal(JsonReply json);
	
	public void responseUIError(String error, String content) {
		this.error = error;
		this.errorProxy.setResponseMapper(this);
	}
	
	public void invokeProxyAction() {
		javax.swing.SwingUtilities.invokeLater(this.error==null?this.errorProxy:this.proxy);
		this.proxy = null;
		this.errorProxy = null;
	}
	
	public void setUIProxy(AbstractUIProxy proxy) {
		this.proxy = proxy;
	}

	public void setErrorProxy(AbstractUIProxy errorProxy) {
		this.errorProxy = errorProxy;
	}

	public String getError() {
		return error;
	}
}
