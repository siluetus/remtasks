package taskqueue.server.client;

import taskqueue.server.TaskCollection;

public class Client {

	protected java.util.UUID uuid;
	protected boolean admin = false;
	protected TaskCollection clientTasks;
	
	
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
