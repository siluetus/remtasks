package taskqueue.client.ui.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.swing.JFileChooser;

import taskqueue.client.request.AbstractRequest;
import taskqueue.client.request.UploadRequest;
import taskqueue.client.response.UploadResponseMapper;
import taskqueue.client.ui.AbstractClientFrame;
import taskqueue.client.ui.JFilePicker;
import taskqueue.client.ui.proxy.AbstractUIProxy;
import taskqueue.client.ui.proxy.RespondSuccessMessage;

public class UploadHandler extends AbstractHandler {
	
	
	public UploadHandler() {
		this.requestClazz = UploadRequest.class;
	}
	
	@Override
	public AbstractUIProxy prepareProxy(AbstractClientFrame frame) {
		return new RespondSuccessMessage(frame, "File successfully uploaded");
	}

	@Override
	protected void tuneRequest(AbstractRequest request) {
		request.setManager(client.getFileUploadedManager());
		try {
			JFileChooser fchooser = this.getFrame().getFilePicker().getFileChooser();
			File f = fchooser.getSelectedFile();
			if(!f.canRead()) {
				throw new Exception("Local file can not be read");
			}
			((UploadRequest) request).setFile(f);
		} catch (Exception e) {
			request.getResponseMapper().responseUIError(e.getMessage(), null);
			((UploadRequest) request).setHasPredefinedErrors(true);
		}
	}

}
