package com.example.tasarim;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mqtt_data")
public class MqttData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 999)
    private String topic;

    @Column(length = 99999)
    private String message;

    @Column(name = "host_name", length = 999)
    private String hostName;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime timestamp;
    @ManyToOne
    private ConnectionDetails connection;

    // Getters and setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
