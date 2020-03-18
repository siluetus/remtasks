package taskqueue.server.works;

public interface ClientThread {
	
	
	public boolean isWorking();
	
	public int getQueueSize();
	
	public void queueWork(Work work);

}
