package taskqueue.client.ui.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import taskqueue.client.Client;
import taskqueue.client.RunClient;
import taskqueue.client.request.AbstractRequest;
import taskqueue.client.request.RegisterRequest;

public class SignUpHandler extends AbstractHandler implements ActionListener {
	
	protected Class<RegisterRequest> requestClazz = RegisterRequest.class;

	@Override
	public void actionPerformed(ActionEvent e) {
		
		super.actionPerformed(e);
	}


	
	
	
	

}
