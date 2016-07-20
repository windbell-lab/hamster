package org.windbell.lab.hamster.mq.active;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;

public class Publisher {
	protected int MAX_DELTA_PERCENT = 1;
	protected Map<String, Double> LAST_PRICES = new HashMap<String, Double>();
	protected static int count = 10;
	protected static int total;
	protected static String brokerURL = "tcp://192.168.254.128:61616";
	protected static transient ConnectionFactory factory;
	protected transient Connection connection;
	protected transient Session session;
	protected transient MessageProducer producer;

	public Publisher() throws JMSException {
		factory = new ActiveMQConnectionFactory(brokerURL);
		connection = factory.createConnection();
		try {
			connection.start();
		} catch (JMSException jmse) {
			connection.close();
			throw jmse;
		}
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		producer = session.createProducer(null);
	}

	public void close() throws JMSException {
		if (connection != null) {
			connection.close();
		}
	}

	protected void sendMessage(String[] stocks) throws JMSException {
		int idx = 0;
		while (true) {
			idx = (int) Math.round(stocks.length * Math.random());
			if (idx < stocks.length) {
				break;
			}
		}
		String stock = stocks[idx];
		Destination destination = session.createTopic("STOCKS." + stock);
		Message message = createStockMessage(stock, session);
		System.out.println(
				"Sending: " + ((ActiveMQMapMessage) message).getContentMap() + " on destination: " + destination);
		producer.send(destination, message);
	}

	protected Message createStockMessage(String stock, Session session) throws JMSException {
		Double value = LAST_PRICES.get(stock);
		if (value == null) {
			value = new Double(Math.random() * 100);
		}

		// lets mutate the value by some percentage
		double oldPrice = value.doubleValue();
		value = new Double(mutatePrice(oldPrice));
		LAST_PRICES.put(stock, value);
		double price = value.doubleValue();

		double offer = price * 1.001;

		boolean up = (price > oldPrice);

		MapMessage message = session.createMapMessage();
		message.setString("stock", stock);
		message.setDouble("price", price);
		message.setDouble("offer", offer);
		message.setBoolean("up", up);
		return message;
	}

	protected double mutatePrice(double price) {
		double percentChange = (2 * Math.random() * MAX_DELTA_PERCENT) - MAX_DELTA_PERCENT;

		return price * (100 + percentChange) / 100;
	}

	public static void main(String[] args) throws JMSException {
		Publisher publisher = new Publisher();
		while (total < 1000) {
			for (int i = 0; i < count; i++) {
				publisher.sendMessage(args);
			}
			total += count;
			System.out.println("Published '" + count + "' of '" + total + "' price messages");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException x) {
			}
		}
		publisher.close();
	}
}
