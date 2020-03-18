package taskqueue.server.events;

import taskqueue.server.manager.WorksManager;
import taskqueue.server.works.Work;

public class ClientThreadListener implements Listener {
	
	protected WorksManager worksManager;
	
	public ClientThreadListener(WorksManager worksManager) {
		this.worksManager = worksManager;
	}
	
	public void processEvent(ClientWorkQueued e) {
		Work work = e.getWork();
		work.setStarted(true);
		worksManager.fireEvent(new ClientWorkStarted(work));
		work.run();
		worksManager.workDone(work);
	}
	
}
