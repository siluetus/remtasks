package taskqueue.client.ui.handlers;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import taskqueue.client.request.AbstractRequest;
import taskqueue.client.request.CheckAuthRequest;
import taskqueue.client.ui.AbstractClientFrame;
import taskqueue.client.ui.proxy.AbstractUIProxy;
import taskqueue.client.ui.proxy.RespondSuccessMessage;

public class SignInHandler extends AbstractHandler {
	
	public SignInHandler() {
		this.requestClazz = CheckAuthRequest.class;
	}
	
	@Override
	public AbstractUIProxy prepareProxy(AbstractClientFrame frame) {
		return new RespondSuccessMessage(frame,"Auth is successed");
	}
	
	

	@Override
	protected void tuneRequest(AbstractRequest request) {
		request.setManager(client.getSignedUpManager());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Возьмем ClientID из текстового поля и поставим его клиенту.
		JTextField signinText = frame.getSigninTextField();
		this.getClient().setClientID(signinText.getText());
		super.actionPerformed(e);
	}

}
