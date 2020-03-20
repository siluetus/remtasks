package taskqueue.client;

import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONAware;

public class ClientWork {

	protected UUID uuid;
	protected String workState;
	protected String workType;
	protected ClientFile jarFile;
	protected ClientFile outFile;
	
	public ClientWork() {
		uuid = null;
		workState = null;
		workType = null;
		jarFile = null;
		outFile = null;
	}
	
	public ClientWork(Map<String,JSONAware> map) {
		this();
		this.parseJsonObject(map);
		
	}
	
	
	public void parseJsonObject(Map<String,JSONAware> map) {

		try {
			this.uuid = map.containsKey("WorkID") ? UUID.fromString(map.get("WorkID").toString()) : null;
		} catch(Exception e) {
			this.uuid = null;
		}
		

		this.workState = map.containsKey("WorkState") ? map.get("WorkState").toString() : null;
		this.workType = map.containsKey("WorkType") ? map.get("WorkType").toString() : null;
		
		
		if(map.containsKey("JarFile") && (map.get("JarFile") instanceof Map) ) {
			this.jarFile = new ClientFile((Map<String,CharSequence>) map.get("JarFile"));
		}

		
		if(map.containsKey("OutFile") && (map.get("OutFile") instanceof Map) ) {
			this.jarFile = new ClientFile((Map<String,CharSequence>) map.get("OutFile"));
		}
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getWorkState() {
		return workState;
	}

	public String getWorkType() {
		return workType;
	}

	public ClientFile getJarFile() {
		return jarFile;
	}

	public ClientFile getOutFile() {
		return outFile;
	}
	
	
	
}
