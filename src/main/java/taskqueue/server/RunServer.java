package taskqueue.server;

import org.eclipse.jetty.util.StringUtil;

import simple.embedding.jetty.HelloWorld;

import java.io.IOException;
import javax.servlet.ServletException;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConnection;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.ServerConnector;

public class RunServer {
	
	/**
     * Get a port, possibly configured from Command line or System property.
     *
     * @param args the command line arguments
     * @param propertyName the property name
     * @param defValue the default value
     * @return the configured port
     */
    public static int getPort(String[] args, String propertyName, int defValue)
    {
        for (String arg : args)
        {
            if (arg.startsWith(propertyName + "="))
            {
                String value = arg.substring(propertyName.length() + 2);
                int port = toInt(value);
                if (isValidPort(port))
                    return port;
            }
        }

        String value = System.getProperty(propertyName);
        int port = toInt(value);
        if (isValidPort(port))
            return port;

        return defValue;
    }
    /**
     * Test if port is in the valid range to be used.
     *
     * @param port the port to test
     * @return true if valid
     */
    private static boolean isValidPort(int port)
    {
        return (port >= 0) && (port <= 65535);
    }

    /**
     * Parse an int, ignoring any {@link NumberFormatException}
     *
     * @param value the string value to parse
     * @return the int (if parsed), or -1 if not parsed.
     */
    private static int toInt(String value)
    {
        if (StringUtil.isBlank(value))
            return -1;

        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException ignored)
        {
            // ignored
            return -1;
        }
    }
    
    
    public static Server createServer(int port) {
    	Server server = new Server();
    	ServerConnector http = new ServerConnector(server);
    	http.setPort(port);
    	http.setHost("localhost");
    	server.addConnector(http);
    	return server;
    	
    }
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		int port = getPort(args, "jetty.http.port", 8080);
		Server server = createServer(port);
		//server.setHandler(new HelloWorld());
		server.initializeContexts();
		server.start();
		server.join();
	}

}
