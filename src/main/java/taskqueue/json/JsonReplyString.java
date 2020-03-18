package taskqueue.json;

import java.io.Serializable;

public class JsonReplyString implements JsonReply,Serializable,Comparable<JsonReplyString>,CharSequence {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6016140005355169634L;
	
	protected String str;
	
	public JsonReplyString(String v) {
		this.str = v;
	}
	public String toString() {
		return str;
		
	}
	public char charAt(int arg0) {
		
		return this.toString().charAt(arg0);
	}
	public int length() {
	
		return this.toString().length();
	}
	public CharSequence subSequence(int arg0, int arg1) {
	
		return this.toString().subSequence(arg0, arg1);
	}

	public int compareTo(JsonReplyString o) {
		
		return this.toString().compareTo(o.toString());
	}
}
