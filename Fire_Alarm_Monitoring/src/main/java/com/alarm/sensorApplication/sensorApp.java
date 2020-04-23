package com.alarm.sensorApplication;

import java.util.Random;

public class sensorApp {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		while(true) {
			try {
				
				Random co2 = new Random();
				Random smokeLvl = new Random();
				
				int levelCo2,levelSmoke;
				
				
				for(int count = 0 ; count <= 1 ; count++) {
				
					levelCo2 = co2.nextInt(10)+1;
					levelSmoke = co2.nextInt(10)+1;
					
					System.out.println("Co2 Level " + levelCo2);
					System.out.println("Smoke Level " + levelSmoke);
					
					
				}
				
				sensorApp.timer();
				
				
				
				
				
			}catch(Exception e){
				
				
			}
			
		}
		
			
		
		
	}
	
	
	public static void timer() {
		
		try {
			Thread.sleep(5000);
			
	}catch (Exception e) {
		
	}

}
	
	
}
