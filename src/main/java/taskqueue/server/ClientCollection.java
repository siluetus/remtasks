package taskqueue.server;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.UUID;

import taskqueue.server.client.Client;

public class ClientCollection {

	protected Dictionary<UUID,Client> clients;
	
	ClientCollection(){
		this.clients = new Hashtable<UUID,Client>();
	}
	
	public String registerClient() {
		UUID uuid = UUID.randomUUID();
		Client client = new Client(uuid);
		this.clients.put(uuid, client);
		return uuid.toString();
	}
	
	
	public Client getClient(String strUUID) {
		UUID uuid = UUID.fromString(strUUID);
		Client client = this.clients.get(uuid);
		return client;
	}
	
	public Enumeration<UUID> getAllClients(){
		return this.clients.keys();
	}
	
}
