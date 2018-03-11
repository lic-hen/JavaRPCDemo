package com.ozias.rmi.client.mgr;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.ozias.rmi.server.api.IService;

public class ClientMgr {
	
	public static void main(String[] args) {
		try {
			IService remote = (IService)Naming.lookup("rmi://127.0.0.1:1229/server");
			
			System.out.println(remote.sayHello());
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
