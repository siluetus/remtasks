package taskqueue.server.works;

import java.util.UUID;

import taskqueue.server.events.Event;
import taskqueue.server.manager.ClientFile;
import taskqueue.server.manager.WorksManager;

public class ClientWork implements Event  {

	protected UUID uuid;
	protected UUID clientID;
	protected AbstractWorkRunner runner;
	
	public ClientWork(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getUuid() {
		return uuid;
	}

	public AbstractWorkRunner getRunner() {
		return runner;
	}

	public void setRunner(AbstractWorkRunner runner) {
		this.runner = runner;
	}

	public void setClientID(UUID clientID) {
		this.clientID = clientID;
	}

	public UUID getClientID() {
		return clientID;
	}

}
