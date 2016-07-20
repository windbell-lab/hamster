package org.windbell.lab.hamster.mq.rabbit.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveRabbitMQMessage {
	private static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] args) {
		Connection connection = null;
		Channel channel = null;
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.254.128");

			connection = factory.newConnection();
			channel = connection.createChannel();

			channel.exchangeDeclare(EXCHANGE_NAME, "topic");
			String queueName = channel.queueDeclare().getQueue();

			if (args.length < 1) {
				System.err.println("Usage: ReceiveLogsTopic [binding_key]...");
				System.exit(1);
			}

			for (String bindingKey : args) {
				channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
			}

			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, true, consumer);

			while (true) {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				String routingKey = delivery.getEnvelope().getRoutingKey();

				System.out.println(" [x] Received '" + routingKey + "':'" + message + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

}
