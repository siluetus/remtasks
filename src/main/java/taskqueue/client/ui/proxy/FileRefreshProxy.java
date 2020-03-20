package taskqueue.client.ui.proxy;

import java.util.ArrayList;

import javax.swing.JList;

import taskqueue.client.ClientFile;
import taskqueue.client.response.FileListResponseMapper;
import taskqueue.client.ui.AbstractClientFrame;

public class FileRefreshProxy extends AbstractUIProxy {
	
	public FileRefreshProxy(AbstractClientFrame frame) {
		this.frame = frame;
		
	}

	public void run() {
		FileListResponseMapper map = (FileListResponseMapper) this.getResponseMapper();
		frame.getFilesList().setListData( map.getFilelist().toArray(new ClientFile[0]));
	}

}
