package taskqueue.client.response;

import taskqueue.client.response.json.JsonReply;
import taskqueue.client.response.json.JsonReplyBool;
import taskqueue.client.response.json.JsonReplyList;
import taskqueue.client.response.json.JsonReplyMap;
import taskqueue.client.response.json.JsonReplyString;

public class SignedUpResponseMapper extends AbstractResponseMapper {

	protected String newClientID;
	protected boolean admin = false;
	
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
		if(json instanceof JsonReplyMap) {
			JsonReplyMap replyMap = (JsonReplyMap) json;
			if(replyMap.containsKey("ClientID")) {
				this.newClientID = replyMap.get("ClientID").toString();
			}
			if(replyMap.containsKey("isAdmin")) {
				this.admin = ((JsonReplyBool) replyMap.get("isAdmin")).getBool();
			}
		}
		this.proxy.setResponseMapper(this);
		return;
	}
	
	
	public String getClientID() {
		return this.newClientID;
	}
	
	public boolean isAdmin() {
		return this.admin;
	}
	
	
	

}
