package com.alarm.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.getRegistry("localhost", 1099);
			LoginFacade server = (LoginFacade) reg.lookup("rmi://localhost/service");
			
			Scanner in = new Scanner(System.in); // initialize scanner obj
			
			// get user input
			System.out.print("Enter username: ");
			String username = in.nextLine();
			System.out.print("Enter password: ");
			String password = in.nextLine();
			
			String response = server.login(username, password);
			
			System.out.println(response);
		} catch (RemoteException | NotBoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
