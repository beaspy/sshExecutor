package com.tlb.ssh;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/** 
 * This class provide interface to execute command on remote Linux. 
 */   
     
public class SSHCommandExecutor {

    private String ipAddress;   
     
    private String username;   
     
    private String password;   
     
    public static final int DEFAULT_SSH_PORT = 22;   
     
     
    public SSHCommandExecutor(final String ipAddress, final String username, final String password) {   
        this.ipAddress = ipAddress;   
        this.username = username;   
        this.password = password;   
    }
     
    public int execute(final String command) {   
        int returnCode = 0;   
        JSch jsch = new JSch();   
        MyUserInfo userInfo = new MyUserInfo();   
     
        try {   
            // Create and connect session.   
            Session session = jsch.getSession(username, ipAddress, DEFAULT_SSH_PORT);   
            session.setPassword(password);   
            session.setUserInfo(userInfo);   
            /* Properties config = new Properties();
            //设置 SSH 连接时不进行公钥确认
  			config.put("StrictHostKeyChecking", "no");
  			session.setConfig(config);*/
            session.connect();   
     
            // Create and connect channel.   
            Channel channel = session.openChannel("exec");   
            ((ChannelExec) channel).setCommand(command);   
     
            channel.setInputStream(null);   
            BufferedReader input = new BufferedReader(new InputStreamReader(channel   
                    .getInputStream()));   
     
            channel.connect();   
            System.out.println("The remote command is: " + command);   
     
            // Get the output of remote command.   
            String line;   
            while ((line = input.readLine()) != null) {   
            	 System.out.println(line);    
            }   
            input.close();   
     
            // Get the return code only after the channel is closed.   
            if (channel.isClosed()) {   
                returnCode = channel.getExitStatus();   
            }   
     
            // Disconnect the channel and session.   
            channel.disconnect();   
            session.disconnect();   
        } catch (JSchException e) {   
            e.printStackTrace();
        } catch (Exception e) {   
            e.printStackTrace();   
        }

        return returnCode;   
    }   
     
}  