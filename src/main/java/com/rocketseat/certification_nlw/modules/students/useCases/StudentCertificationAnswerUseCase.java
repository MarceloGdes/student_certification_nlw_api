package com.rocketseat.certification_nlw.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.rocketseat.certification_nlw.modules.students.entities.AnswersCertificationEntity;
import com.rocketseat.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.certification_nlw.modules.questions.entities.QuestionEntity;
import com.rocketseat.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.rocketseat.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.rocketseat.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.rocketseat.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.rocketseat.certification_nlw.modules.students.entities.StudentEntity;
import com.rocketseat.certification_nlw.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswerUseCase {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {
        // Validando se já existe uma certificação para o usuario

        var hasCertification = this.verifyIfHasCertificationUseCase
                .execute(new VerifyHasCertificationDTO(dto.getEmail(), dto.getTechnology()));
        if (hasCertification) {
            throw new Exception("Você ja tem uma certificação nessa tecnologia");
        }

        // Buscando alternativas das perguntas
        // Buscando as questions referente a technologia dentro do repositório
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());

        List<AnswersCertificationEntity> answersCertifications = new ArrayList<>();

        // Contador de acertos
        // Usamos o Atomic pois não conseguimos manipular uma variavel normal como int
        // dentro de uma expressão lambda
        AtomicInteger correctAwnsers = new AtomicInteger(0);

        // Percorrendo a lista de perguntas respondidas pelo estudante e para cada
        // pergunta
        dto.getQuestionsAnswers().stream().forEach(questionAnswer -> {

            // mapeando as questôes repondidas com as questões do nosso banco
            // cada question vai percorrer a lista de questionsEntity comparando o ID das
            // duas e no final pegando o primeiro resulta (só tem 1 resultado pois é ID) e
            // dadndo um .get para retornar um QuestionEntity
            var question = questionsEntity.stream()
                    .filter(q -> q.getId().equals(questionAnswer.getQuestionID())).findFirst().get();

            // Procurando qual é a alternativa correta da question mapeada
            var findCorrectAlternative = question.getAlternatives().stream()
                    .filter(alternative -> alternative.isCorrect()).findFirst().get();

            // validando se alternativa marcada pelo estudante é a correta
            if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                questionAnswer.setCorrect(true);
                correctAwnsers.incrementAndGet(); // Incrementando a pontuação
            } else {
                questionAnswer.setCorrect(false);
            }

            // Adicionando as perguntas com a validação das repostas no array de
            // answersCertifications que pósteriormente vão ser salvas no banco
            var answersCertificationEntity = AnswersCertificationEntity.builder() // COnstruindo a entidade e setando as
                                                                                  // propriedades
                    .awnserID(questionAnswer.getAlternativeID())
                    .questionID(questionAnswer.getQuestionID())
                    .isCorrect(questionAnswer.isCorrect()).build();

            answersCertifications.add(answersCertificationEntity); // Adicionando no array

        });

        // Verificar se o estudante existe pelo e-mail
        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if (student.isEmpty()) {
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get().getId();
        }

        // Salvar as repostas das perguntas da da certificação
        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
                .technology(dto.getTechnology())
                .studentID(studentID)
                .grade(correctAwnsers.get()) // .get tranforma em int
                .build();

        certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answersCertification -> {
            answersCertification.setCertificationID(certificationStudentEntity.getId());
            answersCertification.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAwnsersCertificationEntities(answersCertifications);

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        return certificationStudentCreated;
    }
}
