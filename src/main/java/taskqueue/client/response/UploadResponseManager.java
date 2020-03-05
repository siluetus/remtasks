package taskqueue.client.response;

import taskqueue.client.Client;

public class UploadResponseManager extends AbstractResponseManager {
	protected Client client;
	
	public UploadResponseManager(Client client){
		this.client = client;
	}
	@Override
	public void manage(AbstractResponseMapper responseMapper) {
		//client.getRunner().addRunner();
	}

}
