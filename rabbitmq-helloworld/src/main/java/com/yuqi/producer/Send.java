package com.yuqi.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String QUEUE_NAME = "Hello";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try(Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel())
        {
            channel.queueDeclare(QUEUE_NAME,false,false,false,null);
            String message = "HELLO RABBITMQ";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            System.out.println(" [x] 发送 '" + message + "'");
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
