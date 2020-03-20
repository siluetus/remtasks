package taskqueue.server.manager;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

import taskqueue.server.client.AbstractClient;
import taskqueue.server.client.ClientThreadFactory;
import taskqueue.server.events.ClientThreadListener;
import taskqueue.server.events.ClientWorkAdded;
import taskqueue.server.events.ClientWorkDone;
import taskqueue.server.events.WorksEventListener;
import taskqueue.server.works.ClientThread;
import taskqueue.server.works.ClientWorkByJar;
import taskqueue.server.works.ClientWorkThread;
import taskqueue.server.works.Work;

public class WorksManager extends AbstractManagerWithEvents implements ClientThreadFactory {

	
	protected Dictionary<UUID,Work> works;
	
	
	@Override
	public void doStart() throws Exception {
		works = new Hashtable<UUID,Work>();
		status |= 2;
	}

	@Override
	public void doStop() throws Exception {
		status &= ~3;
	}

	
	public ClientWorkByJar createWorkFromJar(ClientFile clientFile) throws Exception {
		if(!clientFile.isJar()) {
			throw new Exception("This file is not a JAR");
		}
		UUID workID = UUID.randomUUID();
		ClientWorkByJar clientWork = new ClientWorkByJar(workID,clientFile);
		this.works.put(workID,clientWork);
		return clientWork;
	}

	public ClientThread createClientThread() {
		ClientWorkThread ct = new ClientWorkThread();
		ct.addUserEventListener(new ClientThreadListener(this));
		return ct;
	}
	
	public void queueWork(Work work, AbstractClient client) {
		ClientWorkAdded e = new ClientWorkAdded();
		e.setClient(client);
		e.setWork(work);
		this.fireEvent(e);
	}

	public void workDone(Work work) {
		ClientWorkDone e = new ClientWorkDone();
		e.setWork(work);
		this.fireEvent(e);
	}
	
	public Work getWork(UUID id) {
		return this.works.get(id);
	}
}
