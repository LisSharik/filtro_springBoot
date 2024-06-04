package com.riwi.filtro_spring_boot.infraestructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.filtro_spring_boot.api.dto.request.SurveyRequest;
import com.riwi.filtro_spring_boot.api.dto.response.OptionQuestionBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.QuestionToSurveyResponse;
import com.riwi.filtro_spring_boot.api.dto.response.SurveyBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.SurveyResponse;
import com.riwi.filtro_spring_boot.domain.entities.OptionQuestion;
import com.riwi.filtro_spring_boot.domain.entities.Question;
import com.riwi.filtro_spring_boot.domain.entities.Survey;
import com.riwi.filtro_spring_boot.domain.entities.UserEntity;
import com.riwi.filtro_spring_boot.domain.repositories.SurveyRepository;
import com.riwi.filtro_spring_boot.domain.repositories.UserRepository;
import com.riwi.filtro_spring_boot.infraestructure.abstract_services.ISurveyService;
import com.riwi.filtro_spring_boot.infraestructure.helpers.EmailHelper;
import com.riwi.filtro_spring_boot.util.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyService implements ISurveyService {

    @Autowired
    private final SurveyRepository surveyRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final EmailHelper emailHelper;

    @Override
    public Page<SurveyBasicResponse> getAll(int page, int size) {
        if (page < 0)
            page = 0;
        PageRequest pagination = PageRequest.of(page, size);
        return this.surveyRepository.findAll(pagination).map(this::entityToBasiResponse);
    }

    @Override
    public SurveyResponse get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public SurveyBasicResponse create(SurveyRequest request) {
        Survey newSurvey = this.requestToEntity(request);
        UserEntity creator = this.userRepository.findById(request.getCreatorId()).orElseThrow(()->new IdNotFoundException("UserEntity"));

        newSurvey.setCreator(creator);
        newSurvey.setQuestions(new ArrayList<>());

         if (Objects.nonNull(newSurvey.getCreator())) {
            this.emailHelper.sendMail(newSurvey.getCreator().getEmail());
        }

        return this.entityToBasiResponse(this.surveyRepository.save(newSurvey));
    }

    private Survey find(Long id) {
        return this.surveyRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Survey"));
    }

    private Survey requestToEntity(SurveyRequest survey) {
        return Survey.builder()
                .title(survey.getTitle())
                .description(survey.getDescription())
                .active(survey.getActive())
                .build();
    }

    private SurveyBasicResponse entityToBasiResponse(Survey survey) {
        SurveyBasicResponse surveyBasicResponse = new SurveyBasicResponse();

        BeanUtils.copyProperties(survey, surveyBasicResponse, "questions", "creator");

        return surveyBasicResponse;
    }

    private SurveyResponse entityToResponse(Survey survey){
        SurveyResponse surveyResponse = new SurveyResponse();

        BeanUtils.copyProperties(survey, surveyResponse);

        surveyResponse.setQuestions(this.questionsToResponses(survey.getQuestions()));

        return surveyResponse;
    }



    private List<QuestionToSurveyResponse> questionsToResponses(List<Question> questions) {
        return questions.stream()
                .map(question -> {
                    QuestionToSurveyResponse questionToSurvey = QuestionToSurveyResponse.builder()
                            .id(question.getId())
                            .text(question.getText())
                            .active(question.getActive())
                            .type(question.getType())
                            .optionQuestions(this.optionQuestionsToReponses(question.getOptionquestions()))
                            .build();

                    return questionToSurvey;

                }).toList();
    }

    private List<OptionQuestionBasicResponse> optionQuestionsToReponses(List<OptionQuestion> optionQuestions) {
        return optionQuestions.stream()
                .map(optionQuestion -> {

                    OptionQuestionBasicResponse optionQuestionBasicResponse = OptionQuestionBasicResponse.builder()
                            .id(optionQuestion.getId())
                            .text(optionQuestion.getText())
                            .active(optionQuestion.getActive())
                            .build();

                    return optionQuestionBasicResponse;

                }).toList();
    }

}
