package com.example.service;

import com.example.model.Question;
import com.example.model.QuestionWrapper;
import com.example.model.UserAnswer;
import com.example.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public List<Question> getQuestions() {
        return questionRepo.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepo.findByCategoryIgnoreCase(category);
    }

    public String addQuestion(Question question) {
        questionRepo.save(question);
        return "Success";
    }

    public List<Integer> generateQuestionsForQuiz(String categoryName, Integer numberOfQuestions) {
        List<Integer> questionIds = questionRepo.findRandomQuestionIdsByCategory(categoryName, numberOfQuestions);
        return questionIds;
    }

    public List<QuestionWrapper> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        List<Question> questions = questionRepo.getQuestionFromIds(questionIds);

        questions.stream().forEach(q ->
                questionWrappers.add(new QuestionWrapper(q.getId(), q.getQuestionTitle(),
                        q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4())));
        return questionWrappers;
    }

    public Integer getScore(List<UserAnswer> userAnswers) {
        int score=0;
        for(UserAnswer userAnswer : userAnswers) {
            String rightAns = questionRepo.findRightAnsForId(userAnswer.getId());
            if(rightAns.equals(userAnswer.getAns()))
                score++;
        }
        return score;
    }
}
