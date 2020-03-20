package taskqueue.server.works;

import java.util.UUID;

public abstract class AbstractClientWork implements Work {
	
	protected UUID uuid;
	protected UUID clientID;
	protected byte state = 0;
	
	public UUID getUuid() {
		return uuid;
	}

	public void setClientID(UUID clientID) {
		this.clientID = clientID;
	}

	public UUID getClientID() {
		return clientID;
	}

	public void setQueued(boolean b) {
		if(b) {
			this.state |= 1;
			return;
		}
		this.state &= ~1;
	}

	public void setStarted(boolean b) {
		if(b) {
			this.state |= 2;
			return;
		}
		this.state &= ~2;
	}

	public void setDone(boolean b) {
		if(b) {
			this.state |= 4;
			this.state &= ~3;
			return;
		}
		this.state &= ~4;
	}
	
	public abstract void run();

	public String getStateAsString() {
		switch(this.state & 7) {
		case 1:
			return "Queued";
		case 2:
			return "?Started?";
		case 3:
			return "In Run";
		case 4:
			return "Done";
		}
		return "null";
	}

}
