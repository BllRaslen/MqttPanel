package com.example.tasarim.controller;

import com.example.tasarim.repository.ConnectionDetailsRepository;
import com.example.tasarim.entity.ConnectionDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/connection-details")
public class ConnectionDetailsController {
    private final ConnectionDetailsRepository connectionDetailsRepository;

    // Bağlantı Detayları Kontrolcüsü oluşturucu metodu
    public ConnectionDetailsController(ConnectionDetailsRepository connectionDetailsRepository) {
        this.connectionDetailsRepository = connectionDetailsRepository;
    }

    // Tüm bağlantı detaylarını getiren API endpoint'i
    @GetMapping("/all")
    public List<ConnectionDetails> getAllConnectionDetails() {
        return connectionDetailsRepository.findAll();
    }

    // Farklı bağlantı noktalarını getiren API endpoint'i
    @GetMapping("/distinct-ports")
    public List<Integer> getDistinctPorts() {
        return connectionDetailsRepository.findDistinctPorts();
    }

    // Bağlantı detaylarından özel alanları getiren API endpoint'i
    @GetMapping("/custom-fields")
    public List<Object[]> getCustomFieldsFromConnectionDetails() {
        return connectionDetailsRepository.selectCustomFields();
    }

    // Farklı ana bilgisayar adlarını getiren API endpoint'i
    @GetMapping("/distinct-host-names")
    public List<String> getDistinctHostNames() {
        return connectionDetailsRepository.findDistinctHostNames();
    }

    // Tüm kullanıcı adları ve şifreleri getiren API endpoint'i
    @GetMapping("/all-usernames-and-passwords")
    public List<Object[]> getAllUsernamesAndPasswords() {
        return connectionDetailsRepository.selectAllUsernamesAndPasswords();
    }


}
