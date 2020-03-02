package simple.embedding.jetty;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.util.StringUtil;

public class HelloWorld extends AbstractHandler
{
	
	protected String message;
	
	public HelloWorld() {
		this("<h1>Hello World</h1>");
	}
	
	public HelloWorld(String message){
		super();
		this.message = message;
	}
	
    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException,
        ServletException
    {
        // Declare response encoding and types
        response.setContentType("text/html; charset=utf-8");

        // Declare response status code
        response.setStatus(HttpServletResponse.SC_OK);

        // Write back response
        response.getWriter().println(this.message);

        // Inform jetty that this request has now been handled
        baseRequest.setHandled(true);
    }

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
    public static void main(String[] args) throws Exception
    {
        int port = getPort(args, "jetty.http.port", 8080);
        Server server = new Server(port);
        server.setHandler(new HelloWorld());

        server.start();
        server.join();
    }
}
