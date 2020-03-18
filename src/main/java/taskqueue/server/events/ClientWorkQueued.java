package taskqueue.server.events;

import org.eclipse.jetty.io.ssl.ALPNProcessor.Client;

import taskqueue.server.works.Work;

public class ClientWorkQueued implements Event {
	
	protected Work work;
	
	
	public ClientWorkQueued(Work work) {
		this.work = work;
	}


	public Work getWork() {
		return work;
	}
	
}
