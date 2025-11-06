package com.example.service;

import com.example.feign.QuizInterface;
import com.example.model.*;
import com.example.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizInterface quizInterface;

    public String createQuiz(CreateQuizRequestDto createQuizRequestDto) {

        List<Integer> questionIds = quizInterface.generateQuestionsForQuiz
                (createQuizRequestDto.getCategory(), createQuizRequestDto.getNumOfQuestions())
                .getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(createQuizRequestDto.getTitle());
        quiz.setQuestionIds(questionIds);
        quizRepo.save(quiz);
        return "Success";
    }

    public List<QuestionWrapper> getQuizQuestions(Integer quizId) {
        List<QuestionWrapper> questionWrapperList = new ArrayList<>();
        Optional<Quiz> quiz = quizRepo.findById(quizId);
        if(quiz.isPresent()) {
            questionWrapperList = quizInterface.getQuestionsFromId
                    (quiz.get().getQuestionIds()).getBody();
        }
        return questionWrapperList;
    }

    public Integer calculateResult(Integer id, List<UserAnswer> userAnswers) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        int result = 0;
        if(quiz.isPresent()) {
            result = quizInterface.getScore(userAnswers).getBody();
        }
        return result;
    }
}
