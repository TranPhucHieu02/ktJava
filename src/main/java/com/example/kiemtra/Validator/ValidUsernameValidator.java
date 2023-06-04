package com.example.kiemtra.Validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.kiemtra.Repository.IUserRepository;
import com.example.kiemtra.Validator.annotation.ValidUsername;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public boolean isValid(String  username, ConstraintValidatorContext context) {
        if(userRepository ==null)
            return true;
        return userRepository.findByUsername(username) == null;    
    }
    
}
