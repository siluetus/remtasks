package taskqueue.client.response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import taskqueue.client.ClientFile;
import taskqueue.json.JsonReply;
import taskqueue.json.JsonReplyList;
import taskqueue.json.JsonReplyMap;
import taskqueue.json.JsonReplyString;

public class FileListResponseMapper extends AbstractResponseMapper {
	
	//protected JsonReplyList<JsonReplyMap<JsonReplyString>> filelist;
	
	protected ArrayList<ClientFile> filelist;
	
	@Override
	public void responseUINormal(JsonReply json) {
		try {
			if(json instanceof JsonReplyList<?>) {
				Iterator<JsonReplyMap> it = ((JsonReplyList) json).iterator();
				this.filelist = new ArrayList<ClientFile>();
				while(it.hasNext()) {
					filelist.add(new ClientFile(it.next()));
				}
			}
		} catch(Exception e) {
			
		}
		this.proxy.setResponseMapper(this);
		return; 
	}

	public ArrayList<ClientFile> getFilelist() {
		return filelist;
	}
	
	
}
