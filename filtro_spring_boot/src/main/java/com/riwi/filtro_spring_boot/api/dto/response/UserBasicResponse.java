package com.riwi.filtro_spring_boot.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicResponse {
    private Long id;
    private String name;
    private String email;
    private Boolean active;

}
