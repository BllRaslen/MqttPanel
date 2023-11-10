package com.example.tasarim;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttMessageReceiver {

    private final MqttClient mqttClient;

    @Autowired
    public MqttMessageReceiver(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    // Add methods for handling MQTT messages
}
