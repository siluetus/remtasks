package taskqueue.server.events;

import taskqueue.server.works.Work;

public class ClientWorkStarted implements Event {
	
	protected Work work;
	
	
	public ClientWorkStarted(Work work) {
		this.work = work;
	}


	public Work getWork() {
		return work;
	}
	
}
