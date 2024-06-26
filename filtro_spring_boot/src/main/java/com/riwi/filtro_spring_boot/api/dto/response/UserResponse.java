package com.riwi.filtro_spring_boot.api.dto.response;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends UserBasicResponse{
        private List<SurveyBasicResponse> surveys;

}
