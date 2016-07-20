package hamster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.windbell.lab.hamster.utlis.SystemUtil;

public class CommandTest {
	public static void main(String[]args){
//		String runCommand = SystemUtil.runCommand("ping 127.0.0.1");
//		System.out.println(runCommand.toString());
//		Runtime rt = Runtime.getRuntime();
//		String [] cmd = new String[]{"sh","-c","dir"};
//		try {
//			if(!SystemUtil.isLinux()){
//				cmd[0]="cmd";
//				cmd[1]="/c";
//			}
//			StringBuffer sb=new StringBuffer();
//			
//			Process process = rt.exec(cmd);
//			if(0==process.waitFor()){
//				// InputStream inputStream = process.getInputStream();
//				// OutputStream outputStream = process.getOutputStream();
//				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream())); 
//				String line = "";  
//	            while ((line = input.readLine()) != null) {  
//	                sb.append(new String(line.getBytes("big5"),"UTF-8"));
//	                sb.append("\n");
//	            }  
//	            input.close();  
//				System.out.println("run cmd ok!~系统编码是："+SystemUtil.getSystemEncoding());
//				System.out.println("执行结果是：\n"+sb.toString());
//			}else{
//				System.err.println("rum cmd err!~");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
	}
}
