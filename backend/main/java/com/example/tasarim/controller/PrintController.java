package com.example.tasarim.controller;


import com.example.tasarim.entity.ConnectionDetails;
import com.example.tasarim.repository.ConnectionDetailsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PrintController {

    private final ConnectionDetailsRepository connectionDetailsRepository;

    public PrintController(ConnectionDetailsRepository connectionDetailsRepository) {
        this.connectionDetailsRepository = connectionDetailsRepository;
    }
    @GetMapping("/print")
    public String printData(Model model) {
        List<ConnectionDetails> connectionDetails = connectionDetailsRepository.findAll();
        model.addAttribute("connectionDetails", connectionDetails);
        return "printData";
    }
}

