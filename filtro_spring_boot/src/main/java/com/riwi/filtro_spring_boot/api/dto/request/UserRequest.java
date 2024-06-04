package com.riwi.filtro_spring_boot.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "User name is required")
    @Size(min = 1, max = 100, message = "User name must be between 1 and 100 characters")
    private String name;

    @NotBlank(message = "User email is required")
    @Size(min = 1, max = 100, message = "User email must be between 1 and 100 characters")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "User password is required")
    @Size(min = 1, max = 255, message = "User password must be between 1 and 255 characters")
    private String password;

    @NotNull(message = "User status is required")
    private Boolean active;
}
