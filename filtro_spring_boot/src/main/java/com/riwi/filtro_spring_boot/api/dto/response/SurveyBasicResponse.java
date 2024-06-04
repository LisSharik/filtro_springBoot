package com.riwi.filtro_spring_boot.api.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyBasicResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate creationDate;
    private Boolean active;
  

}
