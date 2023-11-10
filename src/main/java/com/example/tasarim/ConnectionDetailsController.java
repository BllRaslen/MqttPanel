package com.example.tasarim;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/connection-details")
public class ConnectionDetailsController {
    private final ConnectionDetailsRepository connectionDetailsRepository;

    public ConnectionDetailsController(ConnectionDetailsRepository connectionDetailsRepository) {
        this.connectionDetailsRepository = connectionDetailsRepository;
    }

    @GetMapping("/all")
    public List<ConnectionDetails> getAllConnectionDetails() {
        return connectionDetailsRepository.findAll();
    }
    @GetMapping("/custom-fields")
    public List<Object[]> getCustomFieldsFromConnectionDetails() {
        return connectionDetailsRepository.selectCustomFields();
    }
}

