package taskqueue.server.works;

import java.util.UUID;

import taskqueue.server.events.Event;
import taskqueue.server.manager.ClientFile;
import taskqueue.server.manager.WorksManager;

public class ClientWorkByJar extends AbstractClientWork {

	protected ClientFile file;
	
	public ClientWorkByJar(UUID uuid, ClientFile file) {
		this.uuid = uuid;
		this.file = file;
	}
	
	public void run() {
		
		
	}
	
}
