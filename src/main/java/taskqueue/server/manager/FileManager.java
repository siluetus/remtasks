package taskqueue.server.manager;

import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

import taskqueue.server.tasks.ManagedFile;

public class FileManager extends AbstractManager {
	
	public Dictionary<String,ManagedFile> managedFiles;
	
	@Override
	public void doStart() throws Exception {
		managedFiles = new Hashtable<String, ManagedFile>();
		status |= 2;
	}
	
}