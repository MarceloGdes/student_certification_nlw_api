package com.rocketseat.certification_nlw.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modules.students.repositories.CertificationStudentRepository;

@Service // Define que essa camada vai ser uma camada de serviço / use case
public class VerifyIfHasCertificationUseCase {

    @Autowired // Utilizado quando queremos instanciar um componente controlado pelo spring
    private CertificationStudentRepository certificationStudentRepository;

    // execute é um método que executa o que geralmente é especificado no nome da
    // classe, por isso camadas de serviço tem que ser bem específicadas
    public boolean execute(VerifyHasCertificationDTO dto) {

        // Buscando se o usuario tem certificação no banco de dados
        var result = this.certificationStudentRepository
            .findByEmailAndTechnology(dto.getEmail(), dto.getTechnology());

        // validando e retornando se tem ou não a certificação
        if (!result.isEmpty()) {
            return true;
        }
        return false;

    }
}
