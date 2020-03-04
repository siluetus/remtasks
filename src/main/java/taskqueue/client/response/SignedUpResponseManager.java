package taskqueue.client.response;

import taskqueue.client.Client;

public class SignedUpResponseManager extends AbstractResponseManager {
	
	protected Client client;
	
	public SignedUpResponseManager(Client client){
		this.client = client;
	}

	@Override
	public void manage(AbstractResponseMapper responseMapper) {
		SignedUpResponseMapper signedUpResp = (SignedUpResponseMapper)  responseMapper;
		this.client.setClientID(signedUpResp.getClientID());
		this.client.setAdmin(signedUpResp.isAdmin());
	}
}
