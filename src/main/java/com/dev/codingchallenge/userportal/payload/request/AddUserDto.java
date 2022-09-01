package com.dev.codingchallenge.userportal.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDto {

    @Size(max = 128, message = "first name is too long")
    @NotEmpty(message = "first name cannot be empty")
    @NotNull(message = "first name cannot be empty")
    private String firstName;

    @Size(max = 128, message = "last name is too long")
    private String lastName;

    @Email(message = "Email is not valid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",  flags = Pattern.Flag.CASE_INSENSITIVE)
    @NotEmpty(message = "Email cannot be empty")
    @NotNull(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "date of birth cannot be empty")
    private LocalDate dateOfBirth;
}
