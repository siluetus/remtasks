package taskqueue.server.works;

import taskqueue.server.events.AbstractEventDispatcher;
import taskqueue.server.events.ClientWorkQueued;

public class ClientWorkThread extends AbstractEventDispatcher implements ClientThread {

	protected int state = 0;
	protected Thread thread;
	protected Throwable error;
	protected byte num =0;
	
	public ClientWorkThread(){
		thread = new Thread(this);
	}
	
	@Override
	public synchronized boolean isRunning() {
		return (this.state & 2)>0 && thread.isAlive();
	}

	public void start() {
		this.state |= 2; // Set running bit
		thread.start();
	}
	
	@Override
	public synchronized void setFailed(Throwable t) {
		this.error = t;
		this.state |= 8; // Set "Fail" bit
		this.state &= ~7; // Remove all other bits
	}
	
	// Check that this thread is processing a work now
	public synchronized boolean isWorking() {
		return (this.state & 1)>0;
	}
	
	public void dispatchEvents() {
		this.state |= 1; // Setting working state bit
		super.dispatchEvents();
		this.state &= ~1; // removing working state bit
	}
	
	public void run() {
		super.run();
	}
	
	public synchronized int getQueueSize() {
		return this.events.size();
	}

	public void queueWork(Work work) {
		work.setQueued(true);
		this.fireEvent(new ClientWorkQueued(work));
	}

	public void setThreadName(String name) {
		this.thread.setName(String.format("Client-%s-%d", name, this.num));
	}

	public void setThreadNum(byte num) {
		this.num = num;
	}

}
