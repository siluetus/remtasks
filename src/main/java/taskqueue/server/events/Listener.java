package taskqueue.server.events;

public interface Listener {
	
	public void processEvent(Event e);
}
