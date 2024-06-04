package com.riwi.filtro_spring_boot.infraestructure.services;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.filtro_spring_boot.api.dto.request.UserRequest;
import com.riwi.filtro_spring_boot.api.dto.response.UserBasicResponse;
import com.riwi.filtro_spring_boot.domain.entities.UserEntity;
import com.riwi.filtro_spring_boot.domain.repositories.UserRepository;
import com.riwi.filtro_spring_boot.infraestructure.abstract_services.IUserService;
import com.riwi.filtro_spring_boot.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public Page<UserBasicResponse> getAll(int page, int size) {
        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);
        return this.userRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public UserBasicResponse get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public UserBasicResponse create(UserRequest request) {
        UserEntity newUser = this.requestToEntity(request);
        newUser.setSurveys(new ArrayList<>());

        return this.entityToResponse(this.userRepository.save(newUser));
    }

    @Override
    public UserBasicResponse update(UserRequest user,Long id) {
        UserEntity userUpdate = this.find(id);
        BeanUtils.copyProperties(user, userUpdate, "surveys");

        userUpdate.setId(id);
        return this.entityToResponse(this.userRepository.save(userUpdate));

    }


    private UserEntity find(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("UserEntity"));
    }

    private UserEntity requestToEntity(UserRequest user) {
        return UserEntity.builder()
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .active(user.getActive()).build();

    }

    private UserBasicResponse entityToResponse(UserEntity user) {
        UserBasicResponse userBasicResponse = new UserBasicResponse();

        BeanUtils.copyProperties(user, userBasicResponse, "password", "surveys");
        return userBasicResponse;

    }

}
