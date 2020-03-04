package taskqueue.client.ui.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import taskqueue.client.Client;
import taskqueue.client.request.AbstractRequest;
import taskqueue.client.request.RegisterRequest;
import taskqueue.client.response.AbstractResponseMapper;
import taskqueue.client.ui.AbstractClientFrame;
import taskqueue.client.ui.proxy.AbstractUIProxy;
import taskqueue.client.ui.proxy.DefaultErrorProxy;

public abstract class AbstractHandler implements ActionListener {

	protected Client client;
	protected Class<?> requestClazz = AbstractRequest.class;
	protected AbstractClientFrame frame;
	
	public void setClient(Client client) {
		this.client = client;
	}

	public abstract AbstractUIProxy prepareProxy(AbstractClientFrame frame);
	protected abstract void tuneRequest(AbstractRequest request); 

	
	public AbstractUIProxy prepareErrorProxy(AbstractClientFrame frame) {
		return new DefaultErrorProxy(frame);
	}
	
	public void actionPerformed(ActionEvent e) {
		AbstractRequest request = this.client.createRequest(this.requestClazz, this.client.getClientID());
		request.getResponseMapper().setUIProxy(this.prepareProxy(this.frame)).setErrorProxy(this.prepareErrorProxy(this.frame));
		this.tuneRequest(request);
		this.client.getRunner().addRunner(request);
		
	}
	
	public Client getClient() {
		return this.client;
	}

	public AbstractClientFrame getFrame() {
		return frame;
	}

	public void setFrame(AbstractClientFrame frame) {
		this.frame = frame;
	}
	
}
