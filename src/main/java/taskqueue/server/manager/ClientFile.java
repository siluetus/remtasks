package taskqueue.server.manager;

import java.util.UUID;
import org.eclipse.jetty.util.StringUtil;


public class ClientFile {
	
	 protected FileManager fm;
	 protected UUID uuid;
	 protected UUID clientID;
	 protected String fileName;
	 protected String fileType;
	 protected String fullPath;
	 protected boolean isShared = false;
	 
	 ClientFile(FileManager fm){
		 this.fm = fm;
	 }

	public FileManager getFm() {
		return fm;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public UUID getClientID() {
		return clientID;
	}

	public void setClientID(UUID clientID) {
		this.clientID = clientID;
	}

	public boolean isJar() {
		return this.getFileName().endsWith(".jar");
	}

	public boolean isShared() {
		return isShared;
	}

	public void setShared(boolean isShared) {
		this.isShared = isShared;
	}

}
