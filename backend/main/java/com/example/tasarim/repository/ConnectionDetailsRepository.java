package com.example.tasarim.repository;

import com.example.tasarim.entity.ConnectionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface ConnectionDetailsRepository extends JpaRepository<ConnectionDetails, Long> {

    // Belirli bir host ve port için bağlantı detaylarını al
    ConnectionDetails findByHostAndPort(String host, int port);

    // Bağlantı detaylarından özel alanları seç
    @Query("SELECT id, host, port, username FROM ConnectionDetails")
    List<Object[]> selectCustomFields();

    // Farklı bağlantı noktalarını al
    @Query("SELECT DISTINCT cd.port FROM ConnectionDetails cd")
    List<Integer> findDistinctPorts();

    // Farklı ana bilgisayar adlarını al (native query kullanarak)
    @Query(value = "SELECT DISTINCT host FROM connection_details", nativeQuery = true)
    List<String> findDistinctHostNames();

    // Tüm kullanıcı adları ve şifreleri al
    @Query("SELECT username, password FROM ConnectionDetails")
    List<Object[]> selectAllUsernamesAndPasswords();
}
