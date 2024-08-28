package com.ideas2it.hirezy.util;

import com.ideas2it.hirezy.util.ResumeFileTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ResumeFileTypeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidResumeFile {
    String message() default "Invalid resume file type. Only PDF is allowed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}