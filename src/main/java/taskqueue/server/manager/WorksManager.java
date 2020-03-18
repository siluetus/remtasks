package taskqueue.server.manager;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

import taskqueue.server.works.ClientWork;

public class WorksManager extends AbstractManagerWithEvents {

	
	protected Dictionary<UUID,ClientWork> works;
	
	
	@Override
	public void doStart() throws Exception {
		works = new Hashtable<UUID,ClientWork>();
		status |= 2;
	}

	@Override
	public void doStop() throws Exception {
		status &= ~3;
	}

	
	public ClientWork createWorkFromJar(ClientFile clientFile) throws Exception {
		if(!clientFile.isJar()) {
			throw new Exception("This file is not a JAR");
		}
		UUID workID = UUID.randomUUID();
		ClientWork clientWork = new ClientWork(workID);
		this.works.put(workID,clientWork);
		return clientWork;
	}
}
