package com.rocketseat.certification_nlw.modules.students.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "students")
public class StudentEntity {

    // Mapeando campos da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Annotatition que gera o ID de acodo com o parametro strategy
    private UUID id;

    @Column(unique = true, nullable = false)
    // Define que é um dado unico dentro da tabela e que o campo não pode ser nulo.
    private String email;

    // Mapeamento da tabela certificaçoes
    // Nesse caso temos que ver qual é a cardianalidade ou seja um estudante na
    // tabela vai ter varias certificações
    @OneToMany(mappedBy = "studentEntity")
    @JsonBackReference
    private List<CertificationStudentEntity> certificationStudentEntity;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
