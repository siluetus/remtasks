package taskqueue.server.events;

public class CreateClientThreadsOnRegister implements Listener{

	
	protected final int MAX_THREADS = 3;
	
	public CreateClientThreadsOnRegister() {
		
	}
	
	public void processEvent(ClientRegisteredEvent e) throws Exception {
		
	}
	
}
