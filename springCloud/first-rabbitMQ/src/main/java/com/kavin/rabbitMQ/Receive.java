package com.kavin.rabbitMQ;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * 根据queueName 接受消息队列中的消息
 * @author Administrator
 *
 */
public class Receive {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		
		String queueName = "hello";
		channel.queueDeclare(queueName, false, false, false, null);
		
		Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("接收到的消息：" + msg);
			}
		};
		
		channel.basicConsume(queueName, consumer);
	}
}

