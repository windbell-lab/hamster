package org.windbell.lab.hamster.mq.rabbit.base;

import java.io.IOException;
import java.util.Date;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SendQabbitMQMessage {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws IOException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.254.128");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		boolean b = true;
		while (b) {
			String message = "Hello now is :" + (new Date());
			Thread.sleep(5000);
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(">>message is send");
		}
		channel.close();
		connection.close();
	}

}
