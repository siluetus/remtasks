package taskqueue.server.events;

import taskqueue.server.works.Work;

public class ClientWorkDone implements Event{
	
	protected Work work;

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}
	
}
