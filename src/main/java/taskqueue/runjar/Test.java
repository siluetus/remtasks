package taskqueue.runjar;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Test {

	public static void main(String[] args) {
		try {
			
		    File jarfile = new File("Run-Task1.jar");
		    JarFile jar = new JarFile(jarfile);
		    Manifest manifest = jar.getManifest();
		    Attributes attrs = manifest.getMainAttributes();
		    String mainClassName = attrs.getValue("Main-Class");
		    
		    URL url = new URL("file", null, jarfile.getAbsolutePath());
		    ClassLoader cl = new URLClassLoader(new URL[] {url});
		    Class systemCls = cl.loadClass("System");
		    Method sysSetOut = systemCls.getMethod("setOut",  new Class[] {PrintStream.class});
		    //sysSetOut.invoke(systemCls, );
		    Class mainClass = cl.loadClass(mainClassName);
		    Method mainMethod = mainClass.getMethod("main", new Class[] {String[].class});
		    String[] args2 = new String[0];
		    //System.arraycopy(args, 1, args2, 0, args.length - 1);
		    mainMethod.invoke(mainClass, new Object[] {args2});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
