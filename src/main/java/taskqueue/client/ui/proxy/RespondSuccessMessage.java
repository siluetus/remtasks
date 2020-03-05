package taskqueue.client.ui.proxy;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RespondSuccessMessage extends AbstractUIProxy {
	
	protected String message;
	protected JFrame frame;
	
	public RespondSuccessMessage(JFrame frame, String message){
		this.frame = frame;
		this.message = message;
		
	}
	public void run() {
		JOptionPane.showMessageDialog(this.frame,
			    message);		
	}

	
	
}
