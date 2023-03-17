package com.mycompany.consumidor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.Channel;
import com.rabbitmq.client.AMQP.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.mycompany.consumidor.ConexionDB;
import java.io.StringReader;
import static java.lang.System.console;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RDeLaCruz
 */
public class Consumidor {

    private final static String EXCHANGE_NAME = "SCORE_TRANSMISION";
    private final static String[] QUEUE_NAMES = {"cola_niel", "cola_rodrigo", "cola_luis", "cola_ricardo"};

    public static void main(String[] args) throws IOException, TimeoutException, SQLException {
        String url = "jdbc:postgresql://localhost:5432/SCE";
        String usuario = "postgres";
        String contrasena = "admin";

        java.sql.Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/SCE", "postgres", "admin");

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
                    System.out.println(" body cola '" + message + "'");
                    Gson gson = new Gson();
                    Transmision persona = gson.fromJson(message, Transmision.class);
                    Transmision persona1 = gson.fromJson(message, Transmision.class);
                    persona1.getBody().getImagen().setImagen("");

                    System.out.println("body" + gson.toJson(persona.getBody()));
                    Statement stmt;
                    try {
                        Random random = new Random();
                        stmt = conn.createStatement();
                        String sql = "INSERT INTO tramasrecibidas (ncodtrama, strama, dfechahora,nestado,filebase64) VALUES (?, ?, ?,?,?)";
                        PreparedStatement statement = conn.prepareStatement(sql);

                      

//                        JsonObject jsonObject = gson.fromJson(persona.getBody().getImagen().getImagen(), JsonObject.class);

                        System.out.println(persona1.getBody().getActa());

                        statement.setInt(1, random.nextInt(100));
                        statement.setString(2, persona1.getBody().getActa());
                        statement.setDate(3, Date.valueOf(LocalDate.now()));
                        statement.setInt(4, 1);
                        
                        statement.setObject(5, persona.getBody().getImagen().getImagen());
//                        statement.setObject(5, jsonObject, Types.OTHER);
                        
                        statement.executeUpdate();

                        ResultSet rs = stmt.executeQuery("SELECT * FROM tramasrecibidas");
                        while (rs.next()) {
                            System.out.println(rs.getString("strama"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };

            channel.basicConsume(queueName, true, consumer);

        }

    }

}
