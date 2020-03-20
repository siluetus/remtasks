package taskqueue.server.works;

import java.util.UUID;

public interface Work extends Runnable{

	
	public void setQueued(boolean b);
	public void setStarted(boolean b);
	public void setDone(boolean b);
	public UUID getClientID();
	public UUID getUuid();
	public String getStateAsString();
	
}
