package com.rocketseat.certification_nlw.modules.questions.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //Tira a necessidade de setar a entidade em outros campos do código
//Criando esse dto para retornarmos somente dados essenciais na controller
public class QuestionResultDTO {
    
    private UUID id;
    private String technology;
    private String description;
    
    private List<AlternativesResultDTO> alternatives;
}
