package taskqueue.server.events;


import taskqueue.server.client.AbstractClient;
import taskqueue.server.works.Work;

public class ClientWorkAdded implements Event {
	protected Work work;
	protected AbstractClient client;
	
	public Work getWork() {
		return work;
	}
	public AbstractClient getClient() {
		return client;
	}
	public void setWork(Work work) {
		this.work = work;
	}
	public void setClient(AbstractClient client) {
		this.client = client;
	}
	
}
