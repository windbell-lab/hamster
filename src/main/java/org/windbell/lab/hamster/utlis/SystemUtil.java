package org.windbell.lab.hamster.utlis;

import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.ConnectionInfo;
import ch.ethz.ssh2.Session;

/**
 * @author MN
 * @Description: 系统工具类
 * @date: 2016年6月18日 上午7:58:42
 */
public final class SystemUtil {
	/**
	 * @Title: isLinux
	 * @author: MN
	 * @Description: 判断系统是否是Linux
	 * @date: 2015年8月5日 下午4:57:47
	 * @return
	 * @throws 
	 */
	public static boolean isLinux(){
		return System.getProperty("os.name").toLowerCase().indexOf("linux")>=0;
	}
	/**
	 * @Title: getSystemEncoding
	 * @author: MN
	 * @Description: 获取系统默认编码
	 * @date: 2015年8月7日 下午9:29:51
	 * @return
	 * @throws 
	 */
	public static String getSystemEncoding(){
		//Charset.defaultCharset();
		return System.getProperty("file.encoding");
	}
	public static Process execCommand(String command){
		Runtime rt = Runtime.getRuntime();
		String[] cmd = new String[] { "sh", "-c", command };
		if(!SystemUtil.isLinux()){
			cmd[0]="cmd";
			cmd[1]="/c";
		}
		try {
			Process process = rt.exec(cmd);
			if(0==process.waitFor()){
				System.out.println("command run ok~");
			}else{
				System.err.println("command run Failed~");
			}
			return process;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @Title: execCommandOnSSHHost
	 * @author: MN
	 * @Description: 登录ssh 主机并执行命令
	 * @date: 2016年6月18日 上午8:10:52
	 * @param host
	 * @param port
	 * @param user
	 * @param passwd
	 * @param command
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static void execCommandOnSSHHost(String host, int port, String user,
			String passwd, String command) throws IOException,
			UnsupportedEncodingException {
		Connection con = new Connection(host,port);
		con.connect();
		con.authenticateWithPassword(user, passwd);
		Session session = con.openSession();
		session.execCommand(command);
		InputStream stdout = session.getStdout();
		BufferedReader input = new BufferedReader(new InputStreamReader(stdout)); 
		String line = "";  
		StringBuffer sb=new StringBuffer();
		while ((line = input.readLine()) != null) {  
		    sb.append(new String(line.getBytes(),"UTF-8"));
		    sb.append("\n");
		}  
		System.out.println(sb.toString());
		input.close();  
		con.close();
	}
	
	/**
	 * @Title: getFontFamilyNames
	 * @author: MN
	 * @Description: 获取当前系统可用字体
	 * @date: 2016年6月18日 上午8:11:19
	 * @return
	 */
	public static String[] getFontFamilyNames(){
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();  
        return e.getAvailableFontFamilyNames();  
	}
}
