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




/**
 * @author BingoUser
 *
 */
public class RunClient {
	
	protected Frame frame;
	
	protected Deque<Runnable> runnables = new ArrayDeque<Runnable>();
	protected HttpClient httpClient;
	protected Client client;
	protected boolean running = true;

	
	public RunClient(Frame frame) {
		this.frame = frame;
		//this.clientID = "07b8b13-0428-48ea-881f-c14d75812dbb";
		this.client = new Client(this);
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		new RunClient(new Frame()).start("http://localhost:8080");
	}

	private void start(String serverBaseURI) throws InterruptedException {
        try {		
        	
        	this.client.startHttpClient(serverBaseURI);
        	
	        javax.swing.SwingUtilities.invokeLater(new Runnable() { 
	        	 
	            public void run() { 
	  
	            	frame.initFrame2();
	            	frame.setVisible(true);
	            	frame.setRunner(RunClient.this);
	            	RunClient.this.client.initHandlers(frame);
	            	frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	            	frame.addWindowListener(new WindowAdapter() {
	                    @Override
	                    public void windowClosing(WindowEvent e) {
	                    	
	                    	RunClient.this.addRunner(new Runnable() {
								public void run() {
									
									try {
										RunClient.this.client.stopHttpClient();
										RunClient.this.running = false;
										frame.dispose();
										RunClient.this.addRunner(this);
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
	        
	        synchronized(this) {
	        	while(this.running) {
	        		this.wait();
	        		this.runRunner();
	        	}
	        }
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
