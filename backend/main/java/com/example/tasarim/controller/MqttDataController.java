package com.example.tasarim.controller;

import com.example.tasarim.entity.MqttData;
import com.example.tasarim.repository.MqttDataRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mqtt-data")
public class MqttDataController {
    private final MqttDataRepository mqttDataRepository;

    // MqttDataController oluşturucu metodu
    public MqttDataController(MqttDataRepository mqttDataRepository) {
        this.mqttDataRepository = mqttDataRepository;
    }

    // Tüm MQTT verilerini getiren API endpoint'i
    @GetMapping("/all")
    public List<MqttData> getAllMqttData() {
        return mqttDataRepository.findAll();
    }

    // İlişkilendirilmiş verileri getiren API endpoint'i
    @GetMapping("/hepsi")
    public List<Object[]> getHepsiMqttData() {
        return mqttDataRepository.getJoinedData();
    }

    // Belirli bir bağlantıya ait MQTT verilerini getiren API endpoint'i
    @GetMapping("/by-connection")
    public List<MqttData> getMqttDataByConnectionId(@RequestParam Long connectionId) {
        return mqttDataRepository.findByConnectionId(connectionId);
    }

    // Farklı konuları getiren API endpoint'i
    @GetMapping("/distinct-topics")
    public List<String> getDistinctTopics() {
        return mqttDataRepository.findDistinctTopics();
    }

    // Belirli bir konuya ait tüm mesajları getiren API endpoint'i
    @GetMapping("/all-messages/{topic}")
    public List<MqttData> getAllMessagesByTopic(@PathVariable String topic) {
        return mqttDataRepository.findAllByTopic(topic);
    }
}
