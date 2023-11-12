package com.example.tasarim.entity;

import com.example.tasarim.entity.ConnectionDetails;
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

    // Tüm alanlar için getter ve setter metotları

    // ID alanını döndüren metot
    public Long getId() {
        return id;
    }

    // ID alanını ayarlayan metot
    public void setId(Long id) {
        this.id = id;
    }

    // Konu alanını döndüren metot
    public String getTopic() {
        return topic;
    }

    // Konu alanını ayarlayan metot
    public void setTopic(String topic) {
        this.topic = topic;
    }

    // Mesaj alanını döndüren metot
    public String getMessage() {
        return message;
    }

    // Mesaj alanını ayarlayan metot
    public void setMessage(String message) {
        this.message = message;
    }

    // Ana bilgisayar adı alanını döndüren metot
    public String getHostName() {
        return hostName;
    }

    // Ana bilgisayar adı alanını ayarlayan metot
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    // Zaman damgası alanını döndüren metot
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Zaman damgası alanını ayarlayan metot
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Bağlantı alanını döndüren metot
    public ConnectionDetails getConnection() {
        return connection;
    }

    // Bağlantı alanını ayarlayan metot
    public void setConnection(ConnectionDetails connection) {
        this.connection = connection;
    }
}
