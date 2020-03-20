package taskqueue.server.client;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import taskqueue.server.works.ClientThread;
import taskqueue.server.works.ClientWorkThread;

import taskqueue.server.TaskCollection;

public class Client extends AbstractClient{ 

	protected int maxThreads=2;
	
	protected boolean admin = false;
	
	
	protected List<ClientThread> threads = new LinkedList<ClientThread>();
	protected List<UUID> attachedWorks = new LinkedList<UUID>();
	
	public Client(java.util.UUID uuid) {
		super(uuid);
	}
	
	
	public boolean isAdmin() {
		return admin;
	}
	
	public void flyUpToGod() {
		this.admin = true;
	}
	
	
	public ClientThread findThread(ClientThreadFactory threadcreator) {
		
		Iterator<ClientThread> it = threads.iterator();
		ClientThread foundThread = null;
		while(it.hasNext()) {
			ClientThread pretendent = it.next();
			if(!pretendent.isWorking()) {
				foundThread = pretendent;
				break;
			}
			
			if((foundThread==null) || foundThread.getQueueSize() > pretendent.getQueueSize()){
				foundThread = pretendent;
			}
		}
		
		if((foundThread == null || foundThread.isWorking()) && threads.size()<maxThreads && threadcreator!=null ) {
			foundThread = threadcreator.createClientThread();
			this.registerThread(foundThread);
		}
		
		return foundThread;
	}
	
	protected void registerThread(ClientThread thread) {
		threads.add(thread);
		thread.setThreadNum((byte)threads.size());
		thread.setThreadName(this.getId().toString());
		thread.start();
	}
	public void attachWork(UUID clientWorkID) {
		this.attachedWorks.add(clientWorkID);
	}
	
	public UUID[] getClientWorks() {
		return attachedWorks.toArray(new UUID[0]);
	}
}
