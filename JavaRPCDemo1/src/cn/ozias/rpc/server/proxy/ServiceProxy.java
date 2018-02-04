package cn.ozias.rpc.server.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ServiceProxy {

	private Executor executor = Executors.newFixedThreadPool(10);
	
	private final Map<String, Class<?>> serviceRegistry = new HashMap<>();
	
	private boolean isRun;
	
	private int port;
	
	public ServiceProxy(int port)
	{
		this.port = port;
	}
	
	
	public void registerServer(String ifName, Class<?> implClazz)
	{
		serviceRegistry.put(ifName, implClazz);
	}
	
	public void start()
	{
		this.isRun = true;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			
			while(isRun)
			{
				executor.execute(new ServerTask(serverSocket.accept()));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	private class ServerTask implements Runnable
	{
		private Socket socket;
		
		public ServerTask(Socket socket)
		{
			this.socket = socket;
		}
		
		
		@Override
		public void run() {
			
			if (this.socket == null)
			{
				return;
			}
			
			try 
			{
				
				InputStream is = socket.getInputStream();
				ObjectInputStream objis = new ObjectInputStream(is);
				
				String clazzName = objis.readUTF();
				
				Class<?> clazz = serviceRegistry.get(clazzName);
				
				if (clazz == null)
				{
					return;
				}
				
				String methodName = objis.readUTF();
				
				
				Class<?>[] paramTypes = (Class<?>[])objis.readObject();
				
				Method method = clazz.getMethod(methodName, paramTypes);
				
				Object[] paramVals = (Object[]) objis.readObject();
				
				Object result = method.invoke(clazz.getDeclaredConstructor().newInstance(), paramVals);
				
				OutputStream os = socket.getOutputStream();
				
				ObjectOutputStream objos = new ObjectOutputStream(os);
				
				objos.writeObject(result);
				
				
				
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finally
			{
				
			}
			
		}
	}
	
}
