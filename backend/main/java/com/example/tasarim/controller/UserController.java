package com.example.tasarim.controller;


import com.example.tasarim.entity.ConnectionDetails;
import com.example.tasarim.repository.ConnectionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    private
    ConnectionDetailsRepository repo;

    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody ConnectionDetails user){
        repo.save(user);
        return new ResponseEntity<String>("User Saved", HttpStatus.OK);
    }
}
