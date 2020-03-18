package taskqueue.server.works;

import taskqueue.server.events.AbstractEventDispatcher;

public class ClientWorkThread extends AbstractEventDispatcher {

	protected int state = 0;
	protected Thread thread;
	protected Throwable error;
	
	public ClientWorkThread(){
		thread = new Thread(this);
	}
	
	@Override
	public boolean isRunning() {
		return (this.state & 2)>0 && thread.isAlive();
	}

	public void start() {
		this.state |= 2; // Set running bit
		thread.start();
	}
	
	@Override
	public void setFailed(Throwable t) {
		this.error = t;
		this.state |= 8; // Set "Fail" bit
		this.state &= ~7; // Remove all other bits
	}
	
	// Check that this thread is processing a work now
	public boolean isWorking() {
		return (this.state & 1)>0;
	}
	
	public synchronized void dispatchEvents() {
		this.state |= 1; // Setting working state bit
		super.dispatchEvents();
		this.state &= ~1; // removing working state bit
	}
	
	public int getQueueSize() {
		return this.events.size();
	}

}
