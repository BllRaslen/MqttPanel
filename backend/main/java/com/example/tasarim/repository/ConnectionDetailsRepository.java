package com.example.tasarim.repository;

import com.example.tasarim.entity.ConnectionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface ConnectionDetailsRepository extends JpaRepository<ConnectionDetails, Long> {

    ConnectionDetails findByHostAndPort(String host, int port);

    @Query("SELECT id, host, port, username FROM ConnectionDetails")
    List<Object[]> selectCustomFields();

    @Query("SELECT DISTINCT cd.port FROM ConnectionDetails cd")
    List<Integer> findDistinctPorts();
    List<ConnectionDetails> findAll();





}