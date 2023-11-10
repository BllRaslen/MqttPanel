package com.example.tasarim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface MqttDataRepository extends JpaRepository<MqttData, Long> {
    List<MqttData> findByConnectionId(Long connectionId);
   // @Query("SELECT md.id AS mqtt_data_id, md.topic, md.message, md.hostName,cd.id AS connection_details_id, cd.host, cd.port FROM MqttData md INNER JOIN md.connection cd.id")
   // List<Object[]> joinMqttDataAndConnectionDetails();


    @Query("SELECT DISTINCT md.topic FROM MqttData md")
    List<String> findDistinctTopics();

}

