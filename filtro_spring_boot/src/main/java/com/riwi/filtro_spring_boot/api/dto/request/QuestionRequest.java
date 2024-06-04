package com.riwi.filtro_spring_boot.api.dto.request;

import java.util.List;

import com.riwi.filtro_spring_boot.util.enums.QuestionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {

    @NotBlank(message = "Question text is required")
    private String text;

    @NotNull(message = "The question type is requaried")
    private QuestionType type;

    @NotNull(message = "Question status is required")
    private Boolean active;

    @NotNull(message = "Survey ID is required")
    private Long surveyId;

    private List<OptionQuestionRequest> options;
}
