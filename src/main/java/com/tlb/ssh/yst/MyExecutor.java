package com.tlb.ssh.yst;

import java.util.Scanner;

import com.tlb.ssh.SSHCommandExecutor;
import com.tlb.util.Utility;


public class MyExecutor {
	private static String server = "0";
	private static String app = "0";
	private static String executor = "0";
	
	public static void main(String[] args) {
		//初始化
		server = "0";
		app = "0";
		executor = "0";
		while("0".equals(server) || "0".equals(executor)){
			input();
		}
		SSHCommandExecutor sshExecutor = new SSHCommandExecutor(AppServer.server.get(server), AppServer.name, AppServer.pwd);
		if("tail".equals(executor)){
			sshExecutor.execute("tail -f /opt/tomcat-" + app + "/logs/catalina.*");
		}else if("restart".equals(executor)){
			sshExecutor.execute("/opt/restart.sh /opt/tomcat-" + app + "/");
		}else if("update".equals(executor)){
			sshExecutor.execute("svn update /opt/tomcat-" + app + "/webapps/yst_" + app + "_service");
		}else if("updatefull".equals(executor)){
			sshExecutor.execute("svn update --accept theirs-full /opt/tomcat-" + app + "/webapps/yst_" + app + "_service");
		}
		main(args);
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
				System.out.print(serverArray + "|0_return| choose server:");
				server = sc.nextLine();
				if("0".equals(server)){
					return;
				}
			}
		}		
		//executor
		while(!("tail".equals(executor) || "restart".equals(executor) || "update".equals(executor) || "updatefull".equals(executor))){
			System.out.print("update|updatefull|tail|restart|0_return| choose executor:");
			executor = sc.nextLine();
			if("0".equals(executor)){
				server = "0";
				return;
			}
		}
	}
	
}
