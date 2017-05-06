package com.tlb.ssh;

import org.junit.Test;

import com.tlb.util.Utility;

/**
 * @author beaspy
 *
 */
public class SSHTest {

	private String ip = Utility.getProps("/config.properties", "app1");
	private String name = "root";
	private String pwd = "admin@cqyx2015";
	private SSHCommandExecutor sshExecutor = new SSHCommandExecutor(ip, name, pwd);
	
	@Test
	public void tail_reg() {
		sshExecutor.execute("tail -f /opt/tomcat-reg/logs/catalina.out");
	}

	@Test
	public void tail_user() {
		sshExecutor.execute("tail -f /opt/tomcat-user/logs/catalina.out");
	}
	
	@Test
	public void restart() {
		sshExecutor.execute("/opt/restart.sh /opt/bx/tomcat-pay");
	}
 }
