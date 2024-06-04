package com.riwi.filtro_spring_boot.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.filtro_spring_boot.api.dto.request.UserRequest;
import com.riwi.filtro_spring_boot.api.dto.response.UserBasicResponse;
import com.riwi.filtro_spring_boot.infraestructure.abstract_services.IUserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    
    @Autowired
    private final IUserService userService;


    @GetMapping
    public ResponseEntity<Page<UserBasicResponse>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int size
        ) {
            return ResponseEntity.ok(this.userService.getAll(page - 1, size));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserBasicResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.get(id));
    }


    @PostMapping
    public ResponseEntity<UserBasicResponse> insert(@Validated @RequestBody UserRequest request) {
        return ResponseEntity.ok(this.userService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserBasicResponse> update(@Validated @RequestBody UserRequest request, @PathVariable Long id) {
        return ResponseEntity.ok(this.userService.update(request, id));
    }

    
    







}
