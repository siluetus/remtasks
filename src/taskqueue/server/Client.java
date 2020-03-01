package taskqueue.server;

public class Client {

	protected java.util.UUID uuid;
	protected boolean admin = false;
	
	
	public Client(java.util.UUID uuid) {
		this.uuid = uuid;
	}
	
	public java.util.UUID getId() {
		return this.uuid;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public void flyUpToGod() {
		this.admin = true;
	}
	
}
