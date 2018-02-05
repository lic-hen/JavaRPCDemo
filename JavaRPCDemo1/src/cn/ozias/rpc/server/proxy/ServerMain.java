package cn.ozias.rpc.server.proxy;

import cn.ozias.rpc.server.service.IService;
import cn.ozias.rpc.server.service.ServiceImpl;

public class ServerMain {

	public static void main(String[] args) {

		ServiceProxy serviceProxy = new ServiceProxy(12345);
		
		serviceProxy.registerServer(IService.class.getName(), ServiceImpl.class);
		
		serviceProxy.start();
	}

}
