package taskqueue.server.works;

public interface ClientThread {
	
	
	public boolean isWorking();
	
	public int getQueueSize();
	
	public void queueWork(Work work);
	
	public void setThreadName(String name);
	
	public void setThreadNum(byte num);

	public void start();

}
