package com.riwi.filtro_spring_boot.infraestructure.abstract_services;

import com.riwi.filtro_spring_boot.api.dto.request.UserRequest;
import com.riwi.filtro_spring_boot.api.dto.response.UserBasicResponse;

public interface IUserService  extends CrudService<UserRequest, UserBasicResponse, Long>{
    public UserBasicResponse update(UserRequest user, Long id);
}
