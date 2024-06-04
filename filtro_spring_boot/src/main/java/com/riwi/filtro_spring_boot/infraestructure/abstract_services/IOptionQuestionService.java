package com.riwi.filtro_spring_boot.infraestructure.abstract_services;


import com.riwi.filtro_spring_boot.api.dto.request.OptionQuestionRequest;
import com.riwi.filtro_spring_boot.api.dto.response.OptionQuestionBasicResponse;

public interface IOptionQuestionService extends CrudService<OptionQuestionRequest, OptionQuestionBasicResponse, Long>{
    
}
