package com.kavin.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 发送消息到消息队列中
 * 可以在浏览器访问地址http://localhost:15672/#/ 查看消息队列
 * @author Administrator
 *
 */
public class Send {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
//		factory.setHost("localhost");  //全部使用默认配置，如果rabbitMQ服务器更改了配置，此处需要设置更改的配置
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		
		String queueName = "hello";
		channel.queueDeclare(queueName, false, false, false, null);
		
		String message = "Hello ereryBody!";
		channel.basicPublish("", queueName, null, message.getBytes());
		channel.close();
		conn.close();
	}

}
