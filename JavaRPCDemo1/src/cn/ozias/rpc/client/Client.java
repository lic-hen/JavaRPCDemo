package cn.ozias.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Client {

	
	
	@SuppressWarnings("unchecked")
	public <T>T getProxy(Class<?> clazz)
	{
		return (T)Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				Socket socket = new Socket();
				
				
				return null;
			}
		});
	}
}
