package taskqueue.server.events;



import taskqueue.server.client.Client;
import taskqueue.server.manager.ClientManager;
import taskqueue.server.works.ClientThread;
import taskqueue.server.client.ClientThreadFactory;
import taskqueue.server.works.Work;
import org.eclipse.jetty.util.log.Logger;


public class WorksEventListener implements Listener {
	
	protected ClientThreadFactory clientThreadFactory;
	protected ClientManager cm;
	protected Logger logger;
	
	public void setClientManager(ClientManager cm) {
		this.cm = cm;
	}
	
	public WorksEventListener(ClientThreadFactory clientThreadFactory) {
		this.clientThreadFactory = clientThreadFactory;
		this.logger = org.eclipse.jetty.util.log.Log.getLogger(this.getClass());
	}

	public void processEvent(ClientWorkAdded e) {
		if(e.getClient() instanceof Client) {
			Client client = (Client) e.getClient();
			Work work = e.getWork();
			ClientThread thread = client.findThread(this.clientThreadFactory);
			thread.queueWork(work);
			client.attachWork(work.getUuid());
		}
	}
	
	public void processEvent(ClientWorkStarted e) {
		Work work = e.getWork();
		logger.info(String.format("Strating work with GUID %s client id %s",work.getUuid().toString(), work.getClientID().toString()));
	}
	
	public void processEvent(ClientWorkDone e) {
		Work work = e.getWork();
		work.setDone(true);
		cm.fireEvent(e);
		logger.info(String.format("Work was Done with GUID %s client id %s", work.getUuid().toString(), work.getClientID().toString()));
	}
}
