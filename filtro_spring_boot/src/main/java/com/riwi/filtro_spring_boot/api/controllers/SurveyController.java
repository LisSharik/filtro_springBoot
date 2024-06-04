package com.riwi.filtro_spring_boot.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.filtro_spring_boot.api.dto.request.SurveyRequest;
import com.riwi.filtro_spring_boot.api.dto.response.SurveyBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.SurveyResponse;
import com.riwi.filtro_spring_boot.infraestructure.abstract_services.ISurveyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/surveys")
@AllArgsConstructor
public class SurveyController {
    @Autowired
    private final ISurveyService surveyService;

    @GetMapping
    public ResponseEntity<Page<SurveyBasicResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(this.surveyService.getAll(page - 1, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.surveyService.get(id));
    }

    @PostMapping
    public ResponseEntity<SurveyBasicResponse> insert(@Validated @RequestBody SurveyRequest request) {
        return ResponseEntity.ok(this.surveyService.create(request));
    }




}
