package taskqueue.client.response;

import taskqueue.json.JsonReply;
import taskqueue.json.JsonReplyList;
import taskqueue.json.JsonReplyMap;

import java.util.ArrayList;
import java.util.Iterator;

import taskqueue.client.ClientFile;
import taskqueue.client.ClientWork;
public class WorkListResponseMapper extends AbstractResponseMapper {
	
	
	protected ArrayList<ClientWork> works;
	
	@Override
	public void responseUINormal(JsonReply json) {

		if(json instanceof JsonReplyList<?>) {
			Iterator<JsonReplyMap> it = ((JsonReplyList) json).iterator();
			this.works = new ArrayList<ClientWork>();
			while(it.hasNext()) {
				works.add(new ClientWork(it.next()));
			}
		}
	}

	public ArrayList<ClientWork> getWorks() {
		return works;
	}

}
