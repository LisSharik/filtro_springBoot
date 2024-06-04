package com.riwi.filtro_spring_boot.infraestructure.abstract_services;

import com.riwi.filtro_spring_boot.api.dto.request.QuestionRequest;
import com.riwi.filtro_spring_boot.api.dto.response.QuestionResponse;

public interface IQuestionService extends CrudService<QuestionRequest, QuestionResponse, Long> {
    
}
