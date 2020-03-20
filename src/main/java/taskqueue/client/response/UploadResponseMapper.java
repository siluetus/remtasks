package taskqueue.client.response;

import taskqueue.json.JsonReply;

public class UploadResponseMapper extends AbstractResponseMapper {

	@Override
	public void responseUINormal(JsonReply json) {

	}

	
	
	public void setError(String error) {
		this.error = error;
	}
}
