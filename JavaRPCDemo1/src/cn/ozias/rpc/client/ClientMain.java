package cn.ozias.rpc.client;

import java.net.InetSocketAddress;

import cn.ozias.rpc.server.service.IService;

public class ClientMain {

	public static void main(String[] args) {
		
		
		InetSocketAddress remoteAddress  = new InetSocketAddress(12345);
		
		IService proxy = Client.getProxy(IService.class, remoteAddress);
		
		String sayHello = proxy.sayHello();
		
		System.out.println(sayHello);
	}

}
