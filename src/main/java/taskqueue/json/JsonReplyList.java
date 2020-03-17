package taskqueue.json;

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
		
		if(v instanceof Boolean) {
			return super.add((V)new JsonReplyBool((Boolean) v));
		}		
		return super.add(v);
	}

}
