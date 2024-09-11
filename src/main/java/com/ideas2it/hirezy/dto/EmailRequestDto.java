package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailRequestDto {
    @NotBlank(message = "Email ID is Required")
    @Email(message = "Enter Valid mailId like example@gmail.com")
    private String emailId;
}
