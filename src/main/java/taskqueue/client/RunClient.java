/**
 * 
 */
package taskqueue.client;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Deque;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayDeque;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;

import taskqueue.client.request.AbstractRequest;
import taskqueue.client.ui.Frame;
import taskqueue.client.ui.Frame2;
/**
 * @author BingoUser
 *
 */
public class RunClient {
	
	protected Frame2 frame;
	
	protected Deque<Runnable> runnables = new ArrayDeque<Runnable>();
	protected HttpClient httpClient;
	protected String serverURI;
	protected String clientID;
	protected boolean running = true;

	
	public RunClient(Frame2 frame,String serverURI) {
		this.serverURI = serverURI;
		this.frame = frame;
		this.clientID = "07b8b13-0428-48ea-881f-c14d75812dbb";
		this.httpClient = new HttpClient();
		httpClient.setFollowRedirects(true);
		
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		new RunClient(new Frame2(), "http://localhost:8080").start();
	}

	private void start() throws InterruptedException {
		
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() { 
        	 
            public void run() { 
  
            	frame.initFrame();
            	frame.setVisible(true);
            	frame.setRunner(RunClient.this);
            	frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            	frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                    	System.out.println("Test");
                    	RunClient.this.addRunner(new Runnable() {
                    		
							public void run() {
								
								try {
									RunClient.this.httpClient.stop();
									//RunClient.this.running = false;
									RunClient.this.addRunner(new Runnable() {

										public void run() {
											System.err.println("Shutdown");
											System.exit(0);
											//RunClient.this.frame.getWindows()[0].di
										}
										
									});
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
                    		
                    	});
                    	super.windowClosing(e);
                    }           		
            	});
             } 
  
         }); 
        
        try {
			this.httpClient.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        synchronized(this) {
        	while(this.running) {
        		this.wait();
        		this.runRunner();
        	}
        }
	}
	
	protected  void runRunner() {
		if(!runnables.isEmpty()) {
			
			runnables.poll().run();
		}
	}
	
	public synchronized void addRunner(Runnable runner) {
		runnables.add(runner);
		this.notifyAll();
	}
	
	public AbstractRequest createRequest(Class cls, String clientID) {
		try {
			AbstractRequest newRequest = (AbstractRequest) cls.asSubclass(AbstractRequest.class).newInstance();
			Request httpRequest = this.httpClient.newRequest(this.serverURI+newRequest.getLocalPath());
			newRequest.setHttpRequest(httpRequest);
			if(clientID!=null) {
				httpRequest.header("ClientID", clientID);
			}
			return newRequest;
		} catch (Exception e) {

		} 

		return null;
	}
	
	public String getClientID() {
		return this.clientID;
	}
	
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
}
