package com.alarm.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginFacade extends Remote {
	public String login(String username, String password) throws RemoteException;
}
