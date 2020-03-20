package taskqueue.client.ui.handlers;

import javax.swing.JList;

import org.json.simple.JSONArray;

import taskqueue.client.ClientFile;
import taskqueue.client.request.AbstractRequest;
import taskqueue.client.request.FileRunRequest;
import taskqueue.client.ui.AbstractClientFrame;
import taskqueue.client.ui.proxy.AbstractUIProxy;
import taskqueue.client.ui.proxy.RespondSuccessMessage;

public class FileRunHandler extends AbstractHandler {
	
	public FileRunHandler() {
		this.requestClazz = FileRunRequest.class;
	}
	
	
	@Override
	public AbstractUIProxy prepareProxy(AbstractClientFrame frame) {
		return new RespondSuccessMessage(frame, "Work starting success. Go to the \"Works\" tab.");
	}

	@Override
	protected void tuneRequest(AbstractRequest request) {
		JList<ClientFile> list = this.getFrame().getFilesList();
		ClientFile cf = list.getModel().getElementAt(list.getSelectedIndex());
		((FileRunRequest) request).setFile2run(cf);
	}

}
