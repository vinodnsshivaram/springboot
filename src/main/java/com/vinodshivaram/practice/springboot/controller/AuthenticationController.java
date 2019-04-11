package com.vinodshivaram.practice.springboot.controller;

import com.vinodshivaram.practice.springboot.model.Registration;
import com.vinodshivaram.practice.springboot.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Registration payload){
        Registration saved = registrationService.register(payload);
        return new ResponseEntity(saved, HttpStatus.OK);
    }
}
