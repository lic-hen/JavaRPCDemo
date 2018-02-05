package cn.ozias.rpc.client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client<T> {

	
	
	@SuppressWarnings("unchecked")
	public static <T>T getProxy(Class<?> clazz, InetSocketAddress remoteAddress)
	{
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new InvocationHandler() {
			
			@SuppressWarnings("resource")
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				Socket socket = new Socket();
				socket.connect(remoteAddress);
				
				OutputStream os = socket.getOutputStream();
				ObjectOutputStream objos = new ObjectOutputStream(os);
				
				objos.writeUTF(clazz.getName());
				objos.writeUTF(method.getName());
				
				objos.writeObject(method.getParameterTypes());
				objos.writeObject(args);
				
				InputStream is = socket.getInputStream();
				
				ObjectInputStream objis = new ObjectInputStream(is);
				
				Object result = objis.readObject();
				
				return result;
			}
		});
	}
}
