package com.riwi.filtro_spring_boot.api.dto.request;

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
public class SurveyRequest {

    @NotBlank(message = "Survey title is required")
    @Size(min = 1, max = 100, message = "Survey title must be between 1 and 100 characters")
    private String title;

    @NotBlank(message = "Survey description is required")
    private String description;

    @NotNull(message = "Survey status is required")
    private Boolean active;

    @NotNull(message = "User ID is required")
    private Long creatorId;
}
