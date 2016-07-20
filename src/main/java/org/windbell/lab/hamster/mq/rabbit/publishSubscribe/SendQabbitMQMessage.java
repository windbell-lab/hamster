package org.windbell.lab.hamster.mq.rabbit.publishSubscribe;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SendQabbitMQMessage {
	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] args) throws IOException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.254.128");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String message = getMessage(args);
		
	    channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");
	    Thread.sleep(10000);
	    channel.close();
	    connection.close();
	}

	private static String getMessage(String[] strings) {
		if (strings.length < 1)
			return "info: Hello World!";
		return joinStrings(strings, " ");
	}

	private static String joinStrings(String[] strings, String delimiter) {
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuilder words = new StringBuilder(strings[0]);
		for (int i = 1; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}
}
