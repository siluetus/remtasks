package taskqueue.client.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.eclipse.jetty.client.util.InputStreamContentProvider;
import org.eclipse.jetty.http.HttpMethod;

import taskqueue.client.response.UploadResponseMapper;

public class UploadRequest extends AbstractRequest {
	
	protected boolean predefinedErrors = false;
	protected File file;
	
	
	public UploadRequest() {
		this.httpMethod = HttpMethod.PUT;
		this.responseMapper = new UploadResponseMapper();
		this.localPath = "/upload/";
	}

	@Override
	public void run() {
		try {
			this.setStream(this.createInputStream(this.file));
			this.setFileName(this.file.getName());
			
		} catch(Exception e) {
			
			this.getResponseMapper().responseUIError("" + e.getClass().toString() +" " + e.getMessage(), null);
			this.setHasPredefinedErrors(true);
			
		}

		if(this.hasPredefinedErrors()) {
			this.getResponseMapper().invokeProxyAction();
			return;
			
		}
		super.run();
	}
	
	public boolean hasPredefinedErrors() {
		return this.predefinedErrors;
	}
	
	public void setHasPredefinedErrors(boolean hasPredefinedErrors) {
		this.predefinedErrors = hasPredefinedErrors;
	}
	
	public InputStream createInputStream(File file) throws FileNotFoundException {
		return new FileInputStream(file);
	}
	
	public void setFileName(String filename) {
		this.httpRequest.header("Filename", filename);
	}
	
	public void setStream(InputStream fstream) {
		this.httpRequest.content(new InputStreamContentProvider(fstream),"application/octet-stream");
	}

	public void setFile(File f) {
		this.file = f;
	}
	
}
