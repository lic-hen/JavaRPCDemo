package com.ozias.rmi.server.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.ozias.rmi.server.api.IService;

public class ServiceImpl extends UnicastRemoteObject implements IService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceImpl()  throws RemoteException{
	}
	
	@Override
	public String sayHello() throws RemoteException {
		
		String a = "Say Hello";
		
		return a;
	}

}
