package com.example.tasarim.config;

import com.example.tasarim.repository.ConnectionDetailsRepository;
import com.example.tasarim.entity.ConnectionDetails;
import com.example.tasarim.entity.MqttData;
import com.example.tasarim.repository.MqttDataRepository;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@Configuration
public class MqttConfig {

    @Value("${mqtt.broker}")
    private String mqttBroker;

    @Value("${mqtt.username}")
    private String mqttUsername;

    @Value("${mqtt.password}")
    private String mqttPassword;

    @Value("${mqtt.clientId}")
    private String mqttClientId;

    @Value("${mqtt.connectionTimeout}")
    private int mqttConnectionTimeout;

    @Value("${mqtt.keepAliveInterval}")
    private int mqttKeepAliveInterval;

    @Autowired
    private MqttDataRepository messageRepository;

    @Autowired
    private ConnectionDetailsRepository connectionDetailsRepository;

    @Autowired
    private MqttDataRepository mqttDataRepository;

    @Bean
    public MqttClient mqttClient() throws MqttException {
        // MQTT istemcisini oluştur
        MqttClient client = new MqttClient("tcp://" + mqttBroker, mqttClientId);


        // MQTT bağlantı seçeneklerini yapılandır
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(mqttUsername);
        options.setPassword(mqttPassword.toCharArray());
        options.setConnectionTimeout(mqttConnectionTimeout);
        options.setKeepAliveInterval(mqttKeepAliveInterval);

        // MQTT istemci olaylarını işlemek için geriçağırma (callback) ayarla
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                if (cause instanceof MqttException) {
                    MqttException mqttException = (MqttException) cause;
                    System.out.println("MQTT broker bağlantısı kaybedildi: " + mqttException.getMessage());
                    mqttException.printStackTrace(); // Print the full exception stack trace
                } else {
                    System.out.println("MQTT broker bağlantısı kaybedildi: " + cause.getMessage());
                }
            }


            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                // Alınan mesajın içeriğini al
                String payload = new String(message.getPayload(), "UTF-8");
                LocalDateTime timestamp = LocalDateTime.now();

                //System.out.println("message  = " + message);

                // Bağlantı ayrıntılarını oluştur ve kaydet
                ConnectionDetails existingConnection = connectionDetailsRepository.findByHostAndPort(mqttBroker, 1883);

                if (existingConnection == null) {
                    // Create and save a new ConnectionDetails
                    ConnectionDetails newConnectionDetails = new ConnectionDetails();
                    newConnectionDetails.setConnectionTimeout(mqttConnectionTimeout);
                    newConnectionDetails.setPassword(mqttPassword);
                    newConnectionDetails.setUsername(mqttUsername);
                    newConnectionDetails.setPort(1883);
                    newConnectionDetails.setKeepAliveInterval(mqttKeepAliveInterval);
                    newConnectionDetails.setHost(mqttBroker);
                    newConnectionDetails.setClientId(mqttClientId);

                    // Save the new ConnectionDetails
                    connectionDetailsRepository.save(newConnectionDetails);

                    // Set the saved ConnectionDetails to the MqttData object
                    MqttData mqttData = new MqttData();
                    mqttData.setMessage(payload);
                    mqttData.setTimestamp(timestamp);
                    mqttData.setTopic(topic);
                    mqttData.setHostName(mqttBroker);
                    mqttData.setConnection(newConnectionDetails);



                    // Save the MqttData object
                    mqttDataRepository.save(mqttData);
                } else {
                    // Set the saved ConnectionDetails to the MqttData object
                    MqttData mqttData = new MqttData();
                    mqttData.setMessage(payload);
                    mqttData.setTimestamp(timestamp);
                    mqttData.setTopic(topic);
                    mqttData.setHostName(mqttBroker);
                    mqttData.setConnection(existingConnection);



                    // Save the MqttData object
                    mqttDataRepository.save(mqttData);
                }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // Bu örnekte kullanılmıyor
            }
        });

        // MQTT sunucusuna bağlan ve tüm konuları dinlemeye başla
        client.connect(options);
        client.subscribe("#");

        return client;
    }
}
