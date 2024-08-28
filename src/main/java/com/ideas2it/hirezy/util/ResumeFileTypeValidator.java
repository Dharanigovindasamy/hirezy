package com.ideas2it.hirezy.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ResumeFileTypeValidator implements ConstraintValidator<ValidResumeFile, MultipartFile> {

    @Override
    public void initialize(ValidResumeFile constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null) {
            return false;
        }
        String contentType = file.getContentType();
        return "application/pdf".equals(contentType);
    }
}