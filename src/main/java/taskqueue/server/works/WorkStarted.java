package taskqueue.server.works;

import taskqueue.server.events.Event;

public class WorkStarted implements Event {
	
	protected ClientWorkByJar work;
	
	WorkStarted(ClientWorkByJar work){
		this.work = work;
	}

	public ClientWorkByJar getWork() {
		return work;
	}
	
	
	
}
