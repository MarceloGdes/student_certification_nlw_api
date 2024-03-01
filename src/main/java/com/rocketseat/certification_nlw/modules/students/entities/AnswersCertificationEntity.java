package com.rocketseat.certification_nlw.modules.students.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Entity(name = "answers_certification_students")
@Builder
public class AnswersCertificationEntity {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; 

    @Column(name = "certification_id")
    private UUID certificationID;

    //Relaciobnando a entidade certificationStudentEntity com a coluna certification_id dentro dessa tabela
    @ManyToOne
    @JoinColumn(name = "certification_id", insertable = false, updatable = false)
    @JsonBackReference
    private CertificationStudentEntity certificationStudentEntity;


    //Criando a coluna FK student_id
    @Column(name = "student_id")
    private UUID studentID;

    //Relaciobnando a entidade studentEntity com a coluna studendent_id dentro dessa tabela
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity studentEntity;

    

    @Column(name = "question_id")
    private UUID questionID;

    @Column(name = "awnser_id")
    private UUID awnserID;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
