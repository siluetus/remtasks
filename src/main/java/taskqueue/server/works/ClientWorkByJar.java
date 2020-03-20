package taskqueue.server.works;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.UUID;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;



import taskqueue.server.events.Event;
import taskqueue.server.manager.ClientFile;
import taskqueue.server.manager.ClientFolder;
import taskqueue.server.manager.WorksManager;

public class ClientWorkByJar extends AbstractClientWork {

	protected ClientFile file;
	protected ClientFile out;
	
	public ClientWorkByJar(UUID uuid, ClientFile file) {
		this.uuid = uuid;
		this.file = file;
	}
	
	public void run() {
		runByExec();
	}
	
	public void runByExec() {
		try {
			ClientFolder clientFolder = file.getFm().getClientFolder(file.getClientID());
			//File fileOut = new File(clientFolder.getFolder()+File.separatorChar+file.getUuid().toString()+".out.txt");
			Process proc = java.lang.Runtime.getRuntime().exec(String.format("java -jar \"%s\"",file.getFullPath()), null, new File(clientFolder.getFolder()));
			
			InputStream is = 
					proc.getInputStream();
	
			out = clientFolder.putFile(is, this.getUuid().toString()+".out.txt");
		} catch(Exception e) {
			org.eclipse.jetty.util.log.Log.getLogger(this.getClass()).info(String.format("Exception %s of %s", e.getMessage(),e.getClass().toString()));
		}
	}
	
	public void runJarInThread() {
		try {
			
			ClientFolder clientFolder = file.getFm().getClientFolder(file.getClientID());
			File fileOut = new File(clientFolder.getFolder()+File.separatorChar+file.getUuid().toString()+".out.txt");
			
			File jarfile = new File(file.getFullPath());
		    JarFile jar = new JarFile(jarfile);
		    Manifest manifest = jar.getManifest();
		    Attributes attrs = manifest.getMainAttributes();
		    String mainClassName = attrs.getValue("Main-Class");
		    URL url = new URL("file", null, jarfile.getAbsolutePath());
		    ClassLoader cl = new URLClassLoader(new URL[] {url});
		    Class mainClass = cl.loadClass(mainClassName);
		    Method mainMethod = mainClass.getMethod("main", new Class[] {String[].class});
		    String[] args2 = new String[0];
		    
		    Class systemCls = cl.loadClass("java.lang.System");
		    Method sysSetOut = systemCls.getMethod("setOut",  new Class[] {PrintStream.class});
		    sysSetOut.invoke(systemCls,new PrintStream(new FileOutputStream(fileOut)));
		    mainMethod.invoke(mainClass, new Object[] {args2});
		    System.out.println("Test");
		    
		} catch(Exception e) {
			org.eclipse.jetty.util.log.Log.getLogger(this.getClass()).info(String.format("Exception %s of %s", e.getMessage(),e.getClass().toString()));
		}		
	}

	public ClientFile getFile() {
		return file;
	}

	public ClientFile getOut() {
		return out;
	}


}
