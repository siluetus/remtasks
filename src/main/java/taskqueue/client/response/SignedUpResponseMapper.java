package taskqueue.client.response;

import taskqueue.client.response.json.JsonReply;
import taskqueue.client.response.json.JsonReplyList;
import taskqueue.client.response.json.JsonReplyString;

public class SignedUpResponseMapper extends AbstractResponseMapper {

	protected String newClientID;
	
	@Override
	public void responseUINormal(JsonReply json) {
		if(json instanceof JsonReplyList) {
			if(((JsonReplyList) json).size()==1){
				JsonReply result = (JsonReply)((JsonReplyList) json).get(0);
				if(result instanceof JsonReplyString) {
					this.newClientID = result.toString();
				}
			}
		}
		this.proxy.setResponseMapper(this);
		return;
	}
	
	
	public String getClientID() {
		return this.newClientID;
	}
	
	
	

}
