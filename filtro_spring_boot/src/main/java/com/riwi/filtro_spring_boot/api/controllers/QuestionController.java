package com.riwi.filtro_spring_boot.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.filtro_spring_boot.api.dto.request.QuestionRequest;
import com.riwi.filtro_spring_boot.api.dto.response.QuestionResponse;
import com.riwi.filtro_spring_boot.infraestructure.abstract_services.IQuestionService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController {
    
    @Autowired
    private final IQuestionService questionService;


    @PostMapping
    public ResponseEntity<QuestionResponse> insert(@RequestBody QuestionRequest request) {
        
        return ResponseEntity.ok(this.questionService.create(request));
    }
    

}
