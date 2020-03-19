package taskqueue.server.events;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public abstract class AbstractEventDispatcher implements Runnable, Consumer<Listener> {
	
	protected taskqueue.server.events.Event currentEvent;
	protected List<taskqueue.server.events.Listener> listeners =new LinkedList<taskqueue.server.events.Listener>();
	protected Queue<taskqueue.server.events.Event> events =new LinkedList<taskqueue.server.events.Event>();
	
	public void addUserEventListener(taskqueue.server.events.Listener listener) {
		this.listeners.add(listener);
	}
	
	public synchronized void fireEvent(taskqueue.server.events.Event event) {
		events.add(event);
		this.notifyAll();
	}
	
	public void run() {
			try {
				while(isRunning()) {
					this.dispatchEvents();
					this.thisWait();
				}
			} catch (InterruptedException e) {
				this.setFailed(e);
			}
	
	}
	
	
	public synchronized void thisWait() throws InterruptedException {
		super.wait();
	}
	
	public synchronized boolean prepareNextEvent() {
		boolean hasNextEvent = !events.isEmpty();
		currentEvent = hasNextEvent?events.poll():null;
		return hasNextEvent;
	}
	
	public void dispatchEvents() {
		while(this.prepareNextEvent()) {
			listeners.forEach(this);
		}
	}
	public void accept(taskqueue.server.events.Listener l) {
		try {
			Method m = l.getClass().getMethod("processEvent", this.currentEvent.getClass());
			m.invoke(l, this.currentEvent);
		} catch (Exception e) {
			
		}
	}	
	
	
	public abstract boolean isRunning();
	public abstract void setFailed(Throwable t);

}
