package com.example.controller;

import com.example.model.Question;
import com.example.model.QuestionWrapper;
import com.example.model.UserAnswer;
import com.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> allQuestions() {
        List<Question> questions = null;
        try {
            questions = questionService.getQuestions();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questions, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        List<Question> questions = null;
        try {
            questions = questionService.getQuestionsByCategory(category);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questions, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        String status = "Failed";
        try {
            status = questionService.addQuestion(question);
            return new ResponseEntity<>(status, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numberOfQuestions) {
        List<Integer> questionIds = new ArrayList<>();
        try {
            questionIds = questionService.generateQuestionsForQuiz(categoryName, numberOfQuestions);
            return new ResponseEntity<>(questionIds, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionIds, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId
            (@RequestBody List<Integer> questionIds) {
        List<QuestionWrapper> questions = new ArrayList<>();
        try {
            System.out.println(environment.getProperty("local.server.port"));
            questions = questionService.getQuestionsFromId(questionIds);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questions, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("score")
    public ResponseEntity<Integer> getScore(@RequestBody List<UserAnswer> userAnswers) {
        Integer score = 0;
        try {
            score = questionService.getScore(userAnswers);
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(score, HttpStatus.BAD_REQUEST);
    }
}
