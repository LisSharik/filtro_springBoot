package com.riwi.filtro_spring_boot.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.riwi.filtro_spring_boot.domain.entities.UserEntity;

@Service
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
}
