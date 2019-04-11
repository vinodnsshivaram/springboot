package com.vinodshivaram.practice.springboot.controller;

import com.vinodshivaram.practice.springboot.model.Registration;
import com.vinodshivaram.practice.springboot.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users", produces = "application/json", consumes = "application/json")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Registration payload){
        Registration saved = registrationService.register(payload);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }
}
