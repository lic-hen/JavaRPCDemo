package com.ozias.rmi.server.registry;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.ozias.rmi.server.impl.ServiceImpl;

public class ServerRegistry {
	
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(1229);
			
			registry.bind("server", new ServiceImpl());
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

}
