package taskqueue.server.works;

import taskqueue.server.events.AbstractEventDispatcher;

public class AbstractWorkRunner extends AbstractEventDispatcher {

	protected Throwable error;
	protected int state = 0;
	
	
	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return (state | 2)>0;
	}

	@Override
	public void setFailed(Throwable t) {
		this.error = t;
		this.state |= 8;
		this.state &= ~7;
	}
	

}
