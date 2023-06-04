package com.example.kiemtra.Validator;

import com.example.kiemtra.Entity.User;
import com.example.kiemtra.Validator.annotation.ValidUserId;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator  implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if(user==null)
        return true;
        return user.getId() != null;
    }
}
