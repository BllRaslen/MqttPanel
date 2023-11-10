package com.example.tasarim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface ConnectionDetailsRepository extends JpaRepository<ConnectionDetails, Long> {
    @Query("SELECT id, host, port, username FROM ConnectionDetails")
    List<Object[]> selectCustomFields();
}