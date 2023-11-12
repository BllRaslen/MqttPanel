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
    private MqttDataRepository mqttDataRepository;

    @Autowired
    private ConnectionDetailsRepository connectionDetailsRepository;

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

        // MQTT istemci olaylarını işlemek için geri çağırma (callback) ayarla
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                if (cause instanceof MqttException) {
                    MqttException mqttException = (MqttException) cause;
                    System.out.println("MQTT broker bağlantısı kaybedildi: " + mqttException.getMessage());
                    mqttException.printStackTrace(); // Tüm istisna yığınlamasını yazdır
                } else {
                    System.out.println("MQTT broker bağlantısı kaybedildi: " + cause.getMessage());
                }
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                // Alınan mesajın içeriğini al
                String payload = new String(message.getPayload(), "UTF-8");
                LocalDateTime timestamp = LocalDateTime.now();

                // Bağlantı ayrıntılarını oluştur ve kaydet
                ConnectionDetails existingConnection = connectionDetailsRepository.findByHostAndPort(mqttBroker, 1883);

                if (existingConnection == null) {
                    // Yeni bir ConnectionDetails oluştur ve kaydet
                    ConnectionDetails newConnectionDetails = new ConnectionDetails();
                    newConnectionDetails.setConnectionTimeout(mqttConnectionTimeout);
                    newConnectionDetails.setPassword(mqttPassword);
                    newConnectionDetails.setUsername(mqttUsername);
                    newConnectionDetails.setPort(1883);
                    newConnectionDetails.setKeepAliveInterval(mqttKeepAliveInterval);
                    newConnectionDetails.setHost(mqttBroker);
                    newConnectionDetails.setClientId(mqttClientId);

                    // Yeni ConnectionDetails'ı kaydet
                    connectionDetailsRepository.save(newConnectionDetails);

                    // Kaydedilen ConnectionDetails'ı MqttData nesnesine ayarla
                    MqttData mqttData = new MqttData();
                    mqttData.setMessage(payload);
                    mqttData.setTimestamp(timestamp);
                    mqttData.setTopic(topic);
                    mqttData.setHostName(mqttBroker);
                    mqttData.setConnection(newConnectionDetails);

                    // MqttData nesnesini kaydet
                    mqttDataRepository.save(mqttData);
                } else {
                    // Kaydedilen ConnectionDetails'ı MqttData nesnesine ayarla
                    MqttData mqttData = new MqttData();
                    mqttData.setMessage(payload);
                    mqttData.setTimestamp(timestamp);
                    mqttData.setTopic(topic);
                    mqttData.setHostName(mqttBroker);
                    mqttData.setConnection(existingConnection);

                    // MqttData nesnesini kaydet
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
