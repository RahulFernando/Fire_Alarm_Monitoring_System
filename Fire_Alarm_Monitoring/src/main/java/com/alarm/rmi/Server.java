package com.alarm.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Server extends UnicastRemoteObject implements LoginFacade {
	// properties
	private TreeMap clients = new TreeMap<String, String>();
	
	public Server() throws RemoteException {
		
	}

	@Override
	public String login(String username, String password) throws RemoteException {
		init();
		String response = search(username, password);
		
		return response;
	}
	
	// find client
	private String search(String username, String password) {
		String response = "";
		
		Set set = clients.entrySet();
		Iterator itr = set.iterator();
		boolean flag = false;
		
		while(itr.hasNext()) {
			response = "";
			Map.Entry entry = (Map.Entry) itr.next();
			String user = entry.getKey().toString();
			String pass = entry.getValue().toString();
			
			if (username.equals(user)) {
				flag = true;
				if (password.equals(pass)) {
					response = "LOGIN_SUCCESFUL";
				} else {
					response = "PASSWORD_INVALID";
				}
				break;
			}
		}
		
		if (!flag) {
			response = "USERNAME_INVALID";
		}
		
		return response;
	}
	
	private void init() {
		clients.put("admin", "admin");
	}

	public static void main(String[] args) {
		Registry reg;
		try {
			reg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			Server obj = new Server();
			reg.rebind("rmi://localhost/service", obj);
			System.out.println("Server is running...");
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
		


	}

}
