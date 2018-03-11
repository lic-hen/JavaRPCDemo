package com.ozias.rmi.server.api;

/**
 * A Interface extends Remote, indicates its a remote access function
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IService extends Remote {

	/**
	 * Throw RemoteException to remind us that the function is unreliable
	 * 
	 * @return
	 * @throws RemoteException
	 */
	String sayHello() throws RemoteException;
}
