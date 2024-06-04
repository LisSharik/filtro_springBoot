package com.riwi.filtro_spring_boot.api.dto.response;

import com.riwi.filtro_spring_boot.util.enums.QuestionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBasicResponse {
    private Long id;
    private String text;
    private Boolean active;
    private QuestionType type;

}
