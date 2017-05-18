package com.tlb.ssh.yst;

import java.util.HashMap;
import java.util.Map;

public class AppServer {
	
	public static final Map<String, String> server = new HashMap<String, String>();
	public static final String name = "root";
	public static final String pwd = "admin@cqyx2015";

	private AppServer() {
		server.put("1", "172.31.5.1");
		server.put("2", "172.31.5.2");
		server.put("3", "172.31.5.3");
		server.put("4", "172.31.5.4");
		server.put("5", "172.31.5.5");
	}
	
	private static final AppServer appServer = new AppServer();
	
	public static AppServer getAppServer(){
		return appServer;
	}
	
	public static void main(String[] args) {
		System.out.println(AppServer.server);
	}
}
