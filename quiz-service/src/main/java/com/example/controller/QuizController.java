package com.example.controller;

import com.example.model.CreateQuizRequestDto;
import com.example.model.QuestionWrapper;
import com.example.model.UserAnswer;
import com.example.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody CreateQuizRequestDto createQuizRequestDto) {
        try {
            return new ResponseEntity<>(quizService.createQuiz(createQuizRequestDto),HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer quizId) {
        List<QuestionWrapper> questions = null;
        try {
            questions = quizService.getQuizQuestions(quizId);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questions, HttpStatus.NO_CONTENT);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> calculateResult(@PathVariable Integer id,
            @RequestBody List<UserAnswer> userAnswers) {
        try {
            return new ResponseEntity<>(quizService.calculateResult(id, userAnswers), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }
}
