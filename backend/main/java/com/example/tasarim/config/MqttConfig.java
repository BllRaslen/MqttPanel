package com.example.tasarim.config;

import com.example.tasarim.entity.ConnectionDetails;
import com.example.tasarim.entity.MqttData;
import com.example.tasarim.repository.ConnectionDetailsRepository;
import com.example.tasarim.repository.MqttDataRepository;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class MqttConfig {

    @Autowired
    private MqttDataRepository mqttDataRepository;

    @Autowired
    private ConnectionDetailsRepository mqttConnectionDetailsRepository;

    @Autowired
    public ConnectionDetailsRepository connectionDetailsRepository;

    public  ConnectionDetails mqttConnectionDetails;
    @Bean
    public MqttClient mqttClient() throws MqttException {
         mqttConnectionDetails = mqttConnectionDetailsRepository.findById(5L).orElseThrow(
                () -> new IllegalStateException("MQTT connection details not found"));

        MqttClient client = new MqttClient("tcp://" + mqttConnectionDetails.getHost() + ":" + mqttConnectionDetails.getPort(),
                mqttConnectionDetails.getClientId());

        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(mqttConnectionDetails.getUsername());
        options.setPassword(mqttConnectionDetails.getPassword().toCharArray());
        options.setConnectionTimeout(mqttConnectionDetails.getConnectionTimeout());
        options.setKeepAliveInterval(mqttConnectionDetails.getKeepAliveInterval());

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                handleConnectionLost(cause);
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                handleMessageArrived(topic, message);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // This example doesn't use deliveryComplete
            }
        });

        client.connect(options);
        client.subscribe("#");

        return client;
    }

    private void handleConnectionLost(Throwable cause) {
        if (cause instanceof MqttException) {
            MqttException mqttException = (MqttException) cause;
            System.out.println("MQTT broker connection lost: " + mqttException.getMessage());
            mqttException.printStackTrace();
        } else {
            System.out.println("MQTT broker connection lost: " + cause.getMessage());
        }
    }

    private void handleMessageArrived(String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload(), "UTF-8");
        LocalDateTime timestamp = LocalDateTime.now();

        ConnectionDetails existingConnection = connectionDetailsRepository.findByHostAndPort(mqttConnectionDetails.getHost(), 1883);

        if (existingConnection == null) {
            createAndSaveNewConnectionDetails(payload, timestamp, topic);
        } else {
            createAndSaveMqttData(payload, timestamp, topic, existingConnection);
        }
    }

    private void createAndSaveNewConnectionDetails(String payload, LocalDateTime timestamp, String topic) {
        ConnectionDetails newConnectionDetails = new ConnectionDetails();
        newConnectionDetails.setConnectionTimeout(mqttConnectionDetails.getConnectionTimeout());
        newConnectionDetails.setPassword(mqttConnectionDetails.getPassword());
        newConnectionDetails.setUsername(mqttConnectionDetails.getUsername());
        newConnectionDetails.setPort(1883);
        newConnectionDetails.setKeepAliveInterval(mqttConnectionDetails.getKeepAliveInterval());
        newConnectionDetails.setHost(mqttConnectionDetails.getHost());
        newConnectionDetails.setClientId(mqttConnectionDetails.getClientId());

        connectionDetailsRepository.save(newConnectionDetails);

        MqttData mqttData = createMqttData(payload, timestamp, topic, newConnectionDetails);
        mqttDataRepository.save(mqttData);
    }

    private void createAndSaveMqttData(String payload, LocalDateTime timestamp, String topic, ConnectionDetails existingConnection) {
        MqttData mqttData = createMqttData(payload, timestamp, topic, existingConnection);
        mqttDataRepository.save(mqttData);
    }

    private MqttData createMqttData(String payload, LocalDateTime timestamp, String topic, ConnectionDetails connectionDetails) {
        MqttData mqttData = new MqttData();
        mqttData.setMessage(payload);
        mqttData.setTimestamp(timestamp);
        mqttData.setTopic(topic);
        mqttData.setHostName(mqttConnectionDetails.getHost());
        mqttData.setConnection(connectionDetails);
        return mqttData;
    }
}
