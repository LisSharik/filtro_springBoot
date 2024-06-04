package com.riwi.filtro_spring_boot.infraestructure.services;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.filtro_spring_boot.api.dto.request.OptionQuestionRequest;
import com.riwi.filtro_spring_boot.api.dto.request.QuestionRequest;
import com.riwi.filtro_spring_boot.api.dto.response.OptionQuestionBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.QuestionResponse;
import com.riwi.filtro_spring_boot.domain.entities.OptionQuestion;
import com.riwi.filtro_spring_boot.domain.entities.Question;
import com.riwi.filtro_spring_boot.domain.entities.Survey;
import com.riwi.filtro_spring_boot.domain.repositories.OptionQuestionRepository;
import com.riwi.filtro_spring_boot.domain.repositories.QuestionRepository;
import com.riwi.filtro_spring_boot.domain.repositories.SurveyRepository;
import com.riwi.filtro_spring_boot.infraestructure.abstract_services.IQuestionService;
import com.riwi.filtro_spring_boot.util.enums.QuestionType;
import com.riwi.filtro_spring_boot.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService implements IQuestionService {

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired 
    private final OptionQuestionRepository optionQuestionRepository;

    @Autowired
    private final SurveyRepository surveyRepository;

    @Override
    public Page<QuestionResponse> getAll(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public QuestionResponse get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public QuestionResponse create(QuestionRequest request) {
        Question newQuestion = this.requestToEntity(request);
        newQuestion.setOptionquestions(new ArrayList<>());

        Survey survey = this.surveyRepository.findById(request.getSurveyId()).orElseThrow(()-> new IdNotFoundException("Survey"));

        newQuestion.setSurvey(survey);
        Question question = this.questionRepository.save(newQuestion);

        ArrayList<OptionQuestion> totalOptions = new ArrayList<>();
        ArrayList<OptionQuestionBasicResponse> totalOptionsResponse = new ArrayList<>();




        if (request.getType() == QuestionType.CLOSED) {
            request.getOptions().stream()
                        .forEach(option -> {
                            OptionQuestion newOption = this.createOptions(option, question);
                            totalOptions.add(newOption);
                            totalOptionsResponse.add(this.requestOptionBasicResponse(newOption));
                        });


        }

        QuestionResponse questionResponse = this.entityToResponse(newQuestion);
        questionResponse.setOptionQuestions(totalOptionsResponse);


        return questionResponse;

    }

    private Question find(Long id) {
        return this.questionRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Question"));
    }

    private Question requestToEntity(QuestionRequest question){
        return Question.builder()
                        .text(question.getText())
                        .type(question.getType())
                        .active(question.getActive())
                        .build();
    }


    private QuestionResponse entityToResponse(Question question){
        QuestionResponse questionResponse = new QuestionResponse();

        BeanUtils.copyProperties(question, questionResponse, "optionquestions");

        return questionResponse;

    }

    private OptionQuestion createOptions(OptionQuestionRequest request, Question question){
        OptionQuestion newOptionQuestion = this.requestToEntityOptionQuestion(request);
        newOptionQuestion.setQuestion(question);
        this.optionQuestionRepository.save(newOptionQuestion);
        


        return newOptionQuestion;
    }


    private OptionQuestion requestToEntityOptionQuestion(OptionQuestionRequest optionQuestion){
        return OptionQuestion.builder()
                    .text(optionQuestion.getText())
                    .active(optionQuestion.getActive())
                    .build();
    }

    private OptionQuestionBasicResponse requestOptionBasicResponse(OptionQuestion optionQuestion){
        OptionQuestionBasicResponse optionQuestionBasicResponse = new OptionQuestionBasicResponse();

        BeanUtils.copyProperties(optionQuestion, optionQuestionBasicResponse, "question");

    

        return optionQuestionBasicResponse;

    }
}
