package com.example.tasarim;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mqtt-data")
public class MqttDataController {
    private final MqttDataRepository mqttDataRepository;

    public MqttDataController(MqttDataRepository mqttDataRepository) {
        this.mqttDataRepository = mqttDataRepository;
    }

    @GetMapping("/all")
    public List<MqttData> getAllMqttData() {
        return mqttDataRepository.findAll();
    }
    @GetMapping("/by-connection")
    public List<MqttData> getMqttDataByConnectionId(@RequestParam Long connectionId) {
        return mqttDataRepository.findByConnectionId(connectionId);
    }
//    @GetMapping("/join")
//    public List<Object[]> joinMqttDataAndConnectionDetails() {
//        return mqttDataRepository.joinMqttDataAndConnectionDetails();
//    }
    @GetMapping("/distinct-topics")
    public List<String> getDistinctTopics() {
        return mqttDataRepository.findDistinctTopics();
    }
}
