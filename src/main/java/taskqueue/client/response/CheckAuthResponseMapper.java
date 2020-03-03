package taskqueue.client.response;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.client.api.ContentResponse;
import org.json.simple.JSONAware;

import taskqueue.client.response.json.JsonReply;
import taskqueue.client.response.json.JsonReplyList;


public class CheckAuthResponseMapper extends AbstractResponseMapper {

	

	@Override
	public void responseUINormal(JsonReply json) {
		if(json instanceof JsonReplyList) {
			
		}
		return;
		
	}

	
	 
}
