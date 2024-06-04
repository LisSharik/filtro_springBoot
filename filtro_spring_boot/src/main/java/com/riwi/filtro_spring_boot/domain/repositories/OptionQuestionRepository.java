package com.riwi.filtro_spring_boot.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.filtro_spring_boot.domain.entities.OptionQuestion;

@Repository
public interface OptionQuestionRepository extends JpaRepository<OptionQuestion, Long> {
    
}
