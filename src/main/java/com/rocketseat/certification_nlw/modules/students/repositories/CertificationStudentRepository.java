package com.rocketseat.certification_nlw.modules.students.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.rocketseat.certification_nlw.modules.students.entities.CertificationStudentEntity;


@Repository //Define que essa inteface é um repositório controlado pelo Spring
//Extendemos a interface com a classe do JpaRepository<entity, tipo do id da chave primaria>
//Fazendo isso é herdado todos os métodos ja existentes no Spring JPA
public interface CertificationStudentRepository extends JpaRepository<CertificationStudentEntity, UUID>{

    //Criando uma querry personalizado 
    @Query("SELECT c FROM certifications c INNER JOIN c.studentEntity std WHERE std.email = :email AND c.technology = :technology")
    List<CertificationStudentEntity> findByEmailAndTechnology(String email, String technology);

    @Query("SELECT c FROM certifications c ORDER BY c.grade DESC LIMIT 10")
    List<CertificationStudentEntity> findTop10ByOrderGradeDesc();
     
}
