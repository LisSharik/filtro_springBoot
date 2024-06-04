package com.riwi.filtro_spring_boot.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OptionQuestionRequest {

    @NotBlank(message = "Option text is required")
    private String text;

    @NotNull(message = "Option status is required")
    private Boolean active;

    // @NotNull(message = "Question ID is required")
    // private Long questionId;
}
