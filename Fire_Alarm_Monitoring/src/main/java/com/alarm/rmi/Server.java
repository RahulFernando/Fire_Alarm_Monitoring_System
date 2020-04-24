package com.alarm.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import org.json.simple.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Server extends UnicastRemoteObject implements LoginFacade, AlarmFacade{
	// properties
	private TreeMap clients = new TreeMap<String, String>();
	Client client = new Client().create();
	
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
	
	@Override
	public void setSensor(int floor, int room) throws RemoteException {
		try {
			WebResource webResource = client.resource("http://localhost:8080/location/save");
			JSONObject input = new JSONObject();
			input.put("floor_no", floor);
			input.put("room_no", room);
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input.toString());
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
			System.out.println(response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateSensor(int id,int floor, int room) throws RemoteException {
		try {
			WebResource webResource = client.resource("http://localhost:8080/location/update");
			JSONObject input = new JSONObject();
			
			
			input.put("id", id);
			input.put("floor_no", floor);
			input.put("room_no", room);
			input.put("co2", 1);
			input.put("smokeLvl", 1);
			
			ClientResponse response = webResource.type("application/json").put(ClientResponse.class, input.toString());
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
			System.out.println(response.getStatus());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public String getLocation() throws RemoteException {
		try {
			WebResource webResource = client.resource("http://localhost:8080/location");
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed: HTTP error code: " + response.getStatus());
			}
			
			return response.getEntity(String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
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

	@Override
	public void deleteSensor(int id) throws RemoteException {
		// TODO Auto-generated method stub
		
		try {
			
		WebResource webResource = client.resource("http://localhost:8080/location/delete/"+id);
		JSONObject input = new JSONObject();
	
		
		ClientResponse response = webResource.type("application/json").delete(ClientResponse.class, input.toString());
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		
		System.out.println(response.getStatus());
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}



}
