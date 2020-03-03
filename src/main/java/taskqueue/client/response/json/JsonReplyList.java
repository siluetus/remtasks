package taskqueue.client.response.json;

import java.util.ArrayList;

public class JsonReplyList<V> extends ArrayList<V> implements JsonReply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6954566321352503369L;
	
	public void add(String V) {
		this.add((V)(new JsonReplyString(V)));
	}

}
