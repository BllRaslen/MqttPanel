package com.example.tasarim.entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.io.Serializable;

@Entity
@Table(name = "connection_details")
public class ConnectionDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "host")
    private String host;

    @Column(name = "port")
    private int port;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "connection_timeout")
    private int connectionTimeout;

    @Column(name = "keep_alive_interval")
    private int keepAliveInterval;

    // ID alanını döndüren metot
    public Long getId() {
        return id;
    }

    // ID alanını ayarlayan metot
    public void setId(Long id) {
        this.id = id;
    }

    // Host alanını döndüren metot
    public String getHost() {
        return host;
    }

    // Host alanını ayarlayan metot
    public void setHost(String host) {
        this.host = host;
    }

    // Port alanını döndüren metot
    public int getPort() {
        return port;
    }

    // Port alanını ayarlayan metot
    public void setPort(int port) {
        this.port = port;
    }

    // Kullanıcı adı alanını döndüren metot
    public String getUsername() {
        return username;
    }

    // Kullanıcı adı alanını ayarlayan metot
    public void setUsername(String username) {
        this.username = username;
    }

    // Şifre alanını döndüren metot
    public String getPassword() {
        return password;
    }

    // Şifre alanını ayarlayan metot
    public void setPassword(String password) {
        this.password = password;
    }

    // Client ID alanını döndüren metot
    public String getClientId() {
        return clientId;
    }

    // Client ID alanını ayarlayan metot
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    // Bağlantı süre sınırlama alanını döndüren metot
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    // Bağlantı süre sınırlama alanını ayarlayan metot
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    // Keep Alive Interval alanını döndüren metot
    public int getKeepAliveInterval() {
        return keepAliveInterval;
    }

    // Keep Alive Interval alanını ayarlayan metot
    public void setKeepAliveInterval(int keepAliveInterval) {
        this.keepAliveInterval = keepAliveInterval;
    }
}
