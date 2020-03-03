package taskqueue.client.ui.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import taskqueue.client.Client;
import taskqueue.client.RunClient;
import taskqueue.client.request.AbstractRequest;
import taskqueue.client.request.RegisterRequest;

public abstract class AbstractHandler implements ActionListener {

	protected Client client;
	protected AbstractRequest request;
	protected Class<AbstractRequest> requestClazz = AbstractRequest.class;
	
	public void setClient(Client client) {
		this.client = client;
	}

	public void actionPerformed(ActionEvent e) {
		this.prepareRequest();
		RunClient runner = this.client.getRunner();
		runner.addRunner(this.request);
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public void prepareRequest() {
		RunClient runner = this.client.getRunner();
		this.request = this.client.createRequest(RegisterRequest.class, this.client.getClientID());		
	}
	
}
