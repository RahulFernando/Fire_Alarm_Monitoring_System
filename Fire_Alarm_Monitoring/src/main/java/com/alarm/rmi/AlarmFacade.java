package com.alarm.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AlarmFacade extends Remote {
	public void setSensor(String floor, String room) throws RemoteException;;
}
