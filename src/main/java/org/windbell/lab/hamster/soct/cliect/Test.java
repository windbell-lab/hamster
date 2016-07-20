package org.windbell.lab.hamster.soct.cliect;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Test {

    public Test() {
    }

    public static void main(String[] args) throws Exception {
	int port=9999;
	byte[] buf=new byte[1];
	DatagramSocket ds=new DatagramSocket(port);
	DatagramPacket dp=new DatagramPacket(buf, buf.length);
	StringBuffer sb=new StringBuffer(1024);
	ds.receive(dp);
	ds.close();
	for (int i = 0; i < buf.length; i++) {
	    byte b = buf[i];
	    if(b==0){
		break;
	    }
	    sb.append((char)(b));
	}
	System.out.println("接收到广播消息 :"+sb.toString());
    }

}
