package org.windbell.lab.hamster.mq.rabbit.routing;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class ReceiveRabbitMQMessage {
	private static final String EXCHANGE_NAME = "direct_logs";

	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.254.128");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "direct");

		String queueName = channel.queueDeclare().getQueue();

		if (args.length < 1) {
			System.err.println("Usage: ReceiveLogsDirect [info] [warning] [error]");
			System.exit(1);
		}

		for (String severity : args) {
			channel.queueBind(queueName, EXCHANGE_NAME, severity);
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

	}

}
