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

@Data // Annotation do lombok que define automaticamente os getters e setters 
@AllArgsConstructor // Cria um construtor com todos os atributos
@NoArgsConstructor // Vai criar um constutor vazio
@Builder
@Entity(name = "students") //O Spring JPA Entende que quando é utilizado a annotation Entity do jakarta, a entidade tem que ser convertida em uma tabela. e é passado como parametro o nome da tabela
public class StudentEntity { //Entidade dos estudantes no sistema
 
    //Mapeando campos da tabela
    
    @Id //Define como primary key da tabela
    @GeneratedValue(strategy = GenerationType.UUID) //Annotatition que gera o ID de acodo com o parametro strategy
    private UUID id; 

    @Column(unique = true, nullable = false) //Define que é um dado unico dentro da tabela e que o campo não pode ser nulo.
    private String email;
    
    //Mapeamento da tabela certificaçoes
    //Nesse caso temos que ver qual é a cardianalidade ou seja um estudante na tabela vai ter varias certificações

    /* 
     * OneToOne -> 1 para 1
     * OneToMany -> 1 para varios
     * ManyToOne -> varios para 1
     * ManyToMany -> Varios para Varios
     */

    @OneToMany(mappedBy = "studentEntity")
    @JsonBackReference
    private List<CertificationStudentEntity> certificationStudentEntity;

    /* 
     * Em Java, List é uma interface que representa uma coleção ordenada de elementos, onde cada elemento pode aparecer mais de uma vez na coleção. As implementações comuns dessa interface incluem ArrayList, LinkedList, e Vector. Uma List permite a adição, remoção e acesso aos elementos por meio de índices. Ela também fornece métodos para verificar se um elemento está presente na lista, obter o tamanho da lista e iterar sobre os elementos.
     */

    @CreationTimestamp
    private LocalDateTime createdAt;

}
