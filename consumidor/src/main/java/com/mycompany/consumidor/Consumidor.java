

package com.mycompany.consumidor;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.Channel;
import com.rabbitmq.client.AMQP.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author RDeLaCruz
 */
public class Consumidor {

    private final static String EXCHANGE_NAME = "SCORE_TRANSMISION";
    private final static String[] QUEUE_NAMES = {"cola_niel", "cola_rodrigo", "cola_luis", "cola_ricardo"};
    
     public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        com.rabbitmq.client.Connection connection = factory.newConnection();
        com.rabbitmq.client.Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        for (String queueName : QUEUE_NAMES) {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.queueBind(queueName, EXCHANGE_NAME, queueName);
            System.out.println(" [*] Waiting for messages on queue '" + queueName + "'");

            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "' on queue '" + envelope.getRoutingKey() + "'");
                }
            };

            channel.basicConsume(queueName, true, consumer);
            
        }

    }
    
}
