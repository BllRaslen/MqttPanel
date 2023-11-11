package com.example.tasarim.repository;

import com.example.tasarim.entity.MqttData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface MqttDataRepository extends JpaRepository<MqttData, Long> {
    List<MqttData> findByConnectionId(Long connectionId);

    @Query("SELECT DISTINCT md.topic FROM MqttData md")
    List<String> findDistinctTopics();

    List<MqttData> findAll();




}

