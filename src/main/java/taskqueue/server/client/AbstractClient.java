package taskqueue.server.client;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

import org.eclipse.jetty.util.component.LifeCycle;

public abstract class AbstractClient {

	
	protected java.util.UUID uuid;

	
	public AbstractClient(java.util.UUID uuid) {
		this.uuid = uuid; 
	}
	
	
	public java.util.UUID getId() {
		return this.uuid;
	}

	
}
