package taskqueue.json;

import java.util.HashMap;


public class JsonReplyMap<V> extends HashMap<String,V> implements JsonReply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public V put(String k, V v) {
		if(v instanceof String) {
			return super.put(k,((V) new JsonReplyString((String) v)));
		}
		
		if(v instanceof Boolean) {
			return super.put(k,((V) new JsonReplyBool((Boolean) v)));
		}		
		
		return super.put(k, v);
	}

}
