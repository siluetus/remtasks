package taskqueue.server.client;

import taskqueue.server.works.ClientThread;

public interface ClientThreadFactory {
		
	
	public ClientThread createClientThread();
	
}
