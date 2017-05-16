package com.tlb.ssh.yst;

import java.util.Scanner;

import com.tlb.ssh.SSHCommandExecutor;
import com.tlb.util.Utility;


public class MyExecutor {
	private static String server = "0";
	private static String app = "0";
	private static String executor = "0";
	
	public static void main(String[] args) {
		while("0".equals(server) || "0".equals(executor)){
			input();
		}
		
		SSHCommandExecutor sshExecutor = new SSHCommandExecutor(AppServer.server.get(server), AppServer.name, AppServer.pwd);
		if("tail".equals(executor)){
			sshExecutor.execute("tail -f /opt/tomcat-" + app + "/logs/catalina.out" + " -f /opt/tomcat-" + app + "/logs/catalina.out.1");
		}else if("restart".equals(executor)){
			sshExecutor.execute("/opt/restart.sh /opt/tomcat-" + app + "/");
		}
	}
	
	private static void input(){
		Scanner sc = new Scanner(System.in);
		//app
		String serverArray = null;
		while(null == serverArray){
			System.out.print(Utility.getPropKey("/config.properties") + "\n" + "choose tomcat:");
			app = sc.nextLine();
			serverArray = Utility.getProps("/config.properties", app);
		}
		
		//server
		if(serverArray.length() == 1){
			System.out.print(app + " is runnnig at server:" + serverArray + "\n");
			server = serverArray;
		}else{
			while(!serverArray.contains(server)){
				System.out.print(serverArray + "|0-back| choose server:");
				server = sc.nextLine();
				if("0".equals(server)){
					return;
				}
			}
		}
		
		//executor
		while(!("tail".equals(executor) || "restart".equals(executor))){
			System.out.print("tail|restart|0-back| choose executor:");
			executor = sc.nextLine();
			if("0".equals(executor)){
				server = "0";
				return;
			}
		}
	}
}
