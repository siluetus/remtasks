package taskqueue.client.request;

import taskqueue.client.response.SignedUpResponseMapper;

public class CheckAuthRequest extends AbstractRequest {
	
	public CheckAuthRequest(){
		this.responseMapper = new SignedUpResponseMapper();
		this.localPath = "/checkauth/";
	}
	
}
