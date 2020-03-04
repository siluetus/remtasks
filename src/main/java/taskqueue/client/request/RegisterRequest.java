package taskqueue.client.request;

import taskqueue.client.Client;
import taskqueue.client.response.SignedUpResponseMapper;

public class RegisterRequest extends AbstractRequest {
	
	protected Client client;
	
	public RegisterRequest() {
		this.responseMapper = new SignedUpResponseMapper();
		this.localPath = "/register/";
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	

}
