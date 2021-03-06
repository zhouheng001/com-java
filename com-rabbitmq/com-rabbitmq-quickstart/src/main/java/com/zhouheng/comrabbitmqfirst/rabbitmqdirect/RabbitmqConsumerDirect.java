package com.zhouheng.comrabbitmqfirst.rabbitmqdirect;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitmqConsumerDirect {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("192.168.174.128");
        connectionFactory.setPort(5672);
//        connectionFactory.setVirtualHost("zhouheng");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("zhouheng");
        connectionFactory.setPassword("123456");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String queueName ="tuling_queue_01";
        String exchangeName  = "tuling-change-01";
        String exchangeType ="direct";
        String routingKey = "tuling.directchange.key";

        channel.exchangeDeclare(exchangeName,exchangeType,true,false,null);

        channel.queueDeclare(queueName,true,false,true,null);

        channel.queueBind(queueName,exchangeName,routingKey);

        channel.basicQos(0,1,false);

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName,true,queueingConsumer);

        while (true){
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String s = new String(delivery.getBody());
            System.out.println("消费消息:"+s);
        }

    }
}
