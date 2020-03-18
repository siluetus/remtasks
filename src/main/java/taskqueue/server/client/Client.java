package taskqueue.server.client;

import java.util.LinkedList;
import java.util.List;
import taskqueue.server.works.ClientWorkThread;

import taskqueue.server.TaskCollection;

public class Client extends AbstractClient{ 


	protected boolean admin = false;
	
	
	protected List<ClientWorkThread> list = new LinkedList<ClientWorkThread>();
	
	public Client(java.util.UUID uuid) {
		super(uuid);
	}
	
	
	public boolean isAdmin() {
		return admin;
	}
	
	public void flyUpToGod() {
		this.admin = true;
	}
	
	
	public ClientWorkThread findThread() {
		
		
		return null;
	}
	
}
