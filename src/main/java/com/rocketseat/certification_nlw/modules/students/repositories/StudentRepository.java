package com.rocketseat.certification_nlw.modules.students.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rocketseat.certification_nlw.modules.students.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    
    //Sem a necessidade de query pois o JPA sabe que estamos procurando uma entidade pelo email
    //Ira retornarar um Optional do student entity que livbera alguns métodos para varificar se por exemplo a entidade que veio está vazia
    public Optional<StudentEntity> findByEmail(String email);
}
