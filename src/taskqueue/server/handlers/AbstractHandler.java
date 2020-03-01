package taskqueue.server.handlers;

import org.eclipse.jetty.server.Server;

public abstract class AbstractHandler extends org.eclipse.jetty.server.handler.AbstractHandler {

	
	protected taskqueue.server.Server getTaskQServer(){
		Server server = this.getServer();
		if(server instanceof taskqueue.server.Server) {
			return (taskqueue.server.Server) server;
		}
		return null;
	}

}
