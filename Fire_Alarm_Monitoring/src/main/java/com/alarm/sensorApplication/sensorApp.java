package com.alarm.sensorApplication;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONObject;

import com.alarm.com.location.Location;
import com.alarm.rmi.AlarmFacade;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class sensorApp {
	public static void main(String[] args) {
		Client client = new Client().create();
		Gson gson = new Gson();
		

		while(true) {
			try {
				WebResource web = client.resource("http://localhost:8080/location");
				ClientResponse res = web.accept("application/json").get(ClientResponse.class);
				
				if (res.getStatus() != 200) {
					throw new RuntimeException("Failed: HTTP error code: " + res.getStatus());
				}
				
				Location[] location = new Location[100];
				location = gson.fromJson(res.getEntity(String.class), Location[].class);
				
				Random co2 = new Random();
				Random smokeLvl = new Random();
				
				int levelCo2,levelSmoke;
				
				
				for(int count = 0 ; count < location.length ; count++) {
					
					WebResource webResource = client.resource("http://localhost:8080/location/update");
					JSONObject status = new JSONObject();
					
				
					
					levelCo2 = co2.nextInt(10)+1;
					levelSmoke = smokeLvl.nextInt(10)+1;
					
					System.out.println("Co2 Level " + levelCo2);
					System.out.println("Smoke Level " + levelSmoke);
										
					status.put("id" , location[count].getId());
					status.put("floor_no", location[count].getFloor_no());
					status.put("room_no", location[count].getRoom_no());
					status.put("co2" ,levelCo2);
					status.put("smokeLvl" ,levelSmoke);
					
					ClientResponse response = webResource.type("application/json").put(ClientResponse.class, status.toString());
					
					if (response.getStatus() != 200) {
						throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
					}
					
					System.out.println(response.getStatus());
					
					
					
					
					
				}
				
				sensorApp.timer();
				
				
				
				
				

			} catch (Exception e) {
				e.printStackTrace();
			}
					
				
			
		}
	}
			
		
		
	
	
	
	public static void timer() {
		
		try {
			Thread.sleep(10000);
			
	}catch (Exception e) {
		
	}

}
	
	
}
