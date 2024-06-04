package com.riwi.filtro_spring_boot.infraestructure.abstract_services;

import org.springframework.data.domain.Page;

import com.riwi.filtro_spring_boot.api.dto.request.SurveyRequest;
import com.riwi.filtro_spring_boot.api.dto.response.SurveyBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.SurveyResponse;

public interface ISurveyService{
    public Page<SurveyBasicResponse> getAll(int page, int size);
    public SurveyResponse get(Long id);
    public SurveyBasicResponse create(SurveyRequest request);
}
