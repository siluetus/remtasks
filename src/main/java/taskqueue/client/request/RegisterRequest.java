package taskqueue.client.request;

import taskqueue.client.response.SingUpResponseMapper;

public class RegisterRequest extends AbstractRequest {
	
	protected String localPath = "/register";
	
	public RegisterRequest() {
		this.responseMapper = new SingUpResponseMapper();
	}
	

}
