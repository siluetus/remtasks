package taskqueue.server.client;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import taskqueue.server.works.ClientThread;
import taskqueue.server.works.ClientWorkThread;

import taskqueue.server.TaskCollection;

public class Client extends AbstractClient{ 

	protected int maxThreads=3;
	
	protected boolean admin = false;
	
	
	protected List<ClientThread> threads = new LinkedList<ClientThread>();
	protected List<UUID> worksHistory = new LinkedList<UUID>();
	
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
			threads.add(foundThread);
			foundThread.setThreadNum((byte)threads.size());
			foundThread.setThreadName(this.getId().toString());
			foundThread.start();
		}
		
		return foundThread;
	}
	
	public void addHistory() {
		
	}
}
