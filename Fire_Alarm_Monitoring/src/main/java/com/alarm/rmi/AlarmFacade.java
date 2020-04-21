package com.alarm.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.alarm.com.location.Location;

public interface AlarmFacade extends Remote {
	public void setSensor(int floor, int room) throws RemoteException;
	public String getLocation() throws RemoteException;
}
