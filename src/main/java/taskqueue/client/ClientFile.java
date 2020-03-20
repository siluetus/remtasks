package taskqueue.client;

import java.util.Map;

public class ClientFile {

	protected String fileId ;
	protected String fileName;
	
	
	public ClientFile() {
		this.fileId = null;
		this.fileName = null;
	}
	
	public ClientFile(Map<String,CharSequence> map) {
		this.fileId = map.containsKey("FileID") ? map.get("FileID").toString() : null;
		this.fileName = map.containsKey("FileName") ? map.get("FileName").toString() : null;
	}
	
	public String toString() {
		return this.getFileName();
	}

	public String getFileId() {
		return fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
