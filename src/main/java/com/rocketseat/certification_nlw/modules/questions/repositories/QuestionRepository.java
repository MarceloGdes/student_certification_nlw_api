package com.rocketseat.certification_nlw.modules.questions.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.certification_nlw.modules.questions.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID>{

    //Metodo sem a necessidadede Query pois o Spring JPA  consegue mapear o  atributo dentro da question entity
    List<QuestionEntity> findByTechnology(String technology);
    
}