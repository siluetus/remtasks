package taskqueue.server.works;

import taskqueue.server.events.Event;

public class WorkStarted implements Event {
	
	protected ClientWork work;
	
	WorkStarted(ClientWork work){
		this.work = work;
	}

	public ClientWork getWork() {
		return work;
	}
	
	
	
}
