package hamster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.windbell.lab.hamster.utlis.SystemUtil;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.ConnectionInfo;
import ch.ethz.ssh2.Session;

public class Ssh2Test {
	public static void main(String[]args){
		 try {
//			 	Connection connectionSSH = SystemUtil.connectionSSH("10.100.142.104", 2222,"yxgly", "90-pl,");
//			 	Session session = connectionSSH.openSession();
			 	       
			 	///SSH2 /P 2222 /L yxgly /PASSWORD 90-pl, 10.100.142.104"
	            String host = "10.100.142.104";
	            String command = "ps -ef |grep init|grep -v grep";
				int port = 2222;
				String user = "yxgly";
				String passwd = "90-pl,";
				
//				SystemUtil.execCommandOnSSHHost(host, port, user, passwd, command);
				
				Connection con = new Connection(host,port);
				con.connect();
				con.authenticateWithPassword(user, passwd);
				Session session = con.openSession();
				
				session.execCommand(command);
				sessionRuselt(session);
				command=" uptime ";
//				con.connect();
				session=con.openSession();
				session.execCommand(command);
				sessionRuselt(session);
	        } catch (Exception ex) {
	            System.out.println(ex.getLocalizedMessage());
	        }
		 
	}

	private static void sessionRuselt(Session session) throws IOException,
			UnsupportedEncodingException {
		InputStream stdout = session.getStdout();
		BufferedReader input = new BufferedReader(new InputStreamReader(stdout)); 
		String line = "";  
		StringBuffer sb=new StringBuffer();
		while ((line = input.readLine()) != null) {  
		    sb.append(new String(line.getBytes(),"UTF-8"));
		    sb.append("\n");
		}  
		System.out.println(sb.toString());
	}

	
}
