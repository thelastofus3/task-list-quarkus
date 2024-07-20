package com.thelastofus.password;

import com.thelastofus.dto.user.UserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserRequest user = (UserRequest) o;
        boolean valid = user.getPassword() != null && user.getMatchingPassword() != null &&
                user.getPassword().equals(user.getMatchingPassword());
        if (!valid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Passwords do not match")
                    .addPropertyNode("matchingPassword")
                    .addConstraintViolation();
        }
        return valid;
    }
}
