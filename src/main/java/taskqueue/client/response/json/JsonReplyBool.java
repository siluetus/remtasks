package taskqueue.client.response.json;

import java.io.Serializable;

public class JsonReplyBool implements JsonReply,  Serializable, Comparable<Boolean> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2796334402323284914L;
	
	protected boolean bool;
	
	public JsonReplyBool(Boolean bool) {
		this.bool = bool;
	}

	public int compareTo(Boolean o) {
		Boolean bool = this.bool;
		return bool.compareTo(o);
	}
	
	public boolean getBool() {
		return this.bool;
	}
	
}
