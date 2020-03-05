package taskqueue.server.events;

import taskqueue.server.client.AbstractClient;

public class NewClientRegistered implements Event {
	protected AbstractClient client;
}
