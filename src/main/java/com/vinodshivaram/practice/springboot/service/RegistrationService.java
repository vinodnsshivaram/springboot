package com.vinodshivaram.practice.springboot.service;

import com.vinodshivaram.practice.springboot.exceptions.ItemExistsException;
import com.vinodshivaram.practice.springboot.model.Registration;
import com.vinodshivaram.practice.springboot.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@Configuration()
public class RegistrationService implements RegistrationRepository {
    @Autowired
    protected MongoTemplate mongoTemplate;

    @Bean
    public RegistrationService getRegistrationService(){
        return new RegistrationService();
    }

    @Override
    public Registration register(Registration registration) {
        registration.buildDateTimeAndId();
        registration.generateUserId();
        try {
            registration = mongoTemplate.save(registration);
        } catch (DuplicateKeyException e) {
            throw new ItemExistsException("Either email address or username is already in use", e);
        }
        return registration;
    }

    @Override
    public Registration update(Registration registration) {
        return null;
    }

    @Override
    public Registration findByUserId(String userId) {
        return null;
    }

    @Override
    public Registration findByEmailAddress(String emailAddress) {
        return null;
    }
}
