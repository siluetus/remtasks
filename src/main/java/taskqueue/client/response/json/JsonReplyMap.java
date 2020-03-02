package taskqueue.client.response.json;

import java.util.HashMap;


public class JsonReplyMap<K,V> extends HashMap<K,V> implements JSonReply {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void put(K k,String v) {
		this.put(k, (V)(new JsonReplyString(v)));
	}

}
