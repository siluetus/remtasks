package taskqueue.client.response;

import taskqueue.json.JsonReply;
import taskqueue.client.ui.proxy.AbstractUIProxy;

public abstract class AbstractResponseMapper {

	protected AbstractUIProxy proxy;
	protected AbstractUIProxy errorProxy;
	protected String error = null;
	
	public abstract void responseUINormal(JsonReply json);
	
	public void responseUIError(String error, String content) {
		this.error = error;
		this.errorProxy.setResponseMapper(this);
	}
	
	public void invokeProxyAction() {
		javax.swing.SwingUtilities.invokeLater(this.error==null?this.proxy:this.errorProxy);
		this.proxy = null;
		this.errorProxy = null;
	}
	
	public AbstractResponseMapper setUIProxy(AbstractUIProxy proxy) {
		this.proxy = proxy;
		return this;
	}

	public AbstractResponseMapper setErrorProxy(AbstractUIProxy errorProxy) {
		this.errorProxy = errorProxy;
		return this;
	}

	public String getError() {
		return error;
	}

}
