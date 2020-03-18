package taskqueue.server.works;

public class ClientThreadFactory implements taskqueue.server.client.ClientThreadFactory {

	public ClientThread createClientThread() {
		return new ClientWorkThread();
	}

}
