package org.windbell.lab.hamster.soct.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Test {

    public Test() {
    }

    public static void main(String[] args) {
	String host="255.255.255.255";
	int prot=9999;
	String message ="This is test message!";
	StringBuffer sb=new StringBuffer();
	for (int i = 0; i < 100; i++) {
	   sb.append(message);
	   sb.append("|=|");
	}
	
	try {
	    InetAddress addr=InetAddress.getByName(host);
	    DatagramSocket ds= new DatagramSocket();
	    DatagramPacket dp= new DatagramPacket(sb.toString().getBytes(),sb.length(),addr,prot);
	    ds.send(dp);
	    ds.close();
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SocketException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	
	
    }

}
