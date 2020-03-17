package taskqueue.server.manager;

import java.util.UUID;

public class ClientWork implements Runnable {

	protected UUID uuid;
	protected ClientFile clientFile;
	protected WorksManager worksManager;
	
	public ClientWork(UUID uuid,ClientFile clientFile) {
		this.uuid = uuid;
		this.clientFile = clientFile;
	}

	public void run() {
		
		
	}
	
	public void start() {
		
	}

}
