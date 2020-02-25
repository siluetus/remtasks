package taskqueue.server;

import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import simple.embedding.jetty.HelloWorld;
public class Server extends org.eclipse.jetty.server.Server {
	
	
	 
	public void initializeContexts() {
		ContextHandler context = new ContextHandler("/hello");
		context.setHandler(new HelloWorld("<h1> Hello world </h1>"));
	}
	
	public void initializeState() {
		
	}
}
