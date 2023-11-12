package com.example.tasarim.repository;

import com.example.tasarim.entity.MqttData;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface MqttDataRepository extends JpaRepository<MqttData, Long> {

    // Belirli bir bağlantı ID'sine sahip MQTT verilerini al
    List<MqttData> findByConnectionId(Long connectionId);

    // Farklı konuları bul
    @Query("SELECT DISTINCT md.topic FROM MqttData md")
    List<String> findDistinctTopics();

    // Tüm MQTT verilerini al
    List<MqttData> findAll();

    // Bağlantı detayları ve MQTT verilerini birleştirilmiş şekilde al (native query kullanarak)
    @Query(value = "SELECT md.topic, md.timestamp, md.message, md.host_name, " +
            "cd.username, cd.password, cd.port, cd.keep_alive_interval, cd.connection_timeout " +
            "FROM public.connection_details cd " +
            "INNER JOIN public.mqtt_data md ON cd.id = md.connection_id",
            nativeQuery = true)
    List<Object[]> getJoinedData();

    // Belirli bir konuya sahip tüm MQTT verilerini al
    @Query("SELECT md FROM MqttData md WHERE md.topic = :topic")
    List<MqttData> findAllByTopic(String topic);
}
