package com.vinodshivaram.practice.springboot.repository;

import com.vinodshivaram.practice.springboot.model.Registration;

public interface RegistrationRepository {
    Registration register(Registration registration);
    Registration update(Registration registration);
    Registration findByUserId(String userId);
    Registration findByEmailAddress(String emailAddress);
}
