package taskqueue.client.response.json;

import java.util.ArrayList;

public class JsonReplyList<V> extends ArrayList<V> implements JsonReply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6954566321352503369L;
	
	
	public boolean add(V v) {
		if(v instanceof String) {
			return super.add((V)new JsonReplyString(v.toString()));
		}
		return super.add(v);
	}

}
