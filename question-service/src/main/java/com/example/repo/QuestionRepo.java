package com.example.repo;

import com.example.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    List<Question> findByCategoryIgnoreCase(String category);

    @Query(value = "SELECT q.id FROM Question q WHERE q.category = :categoryName ORDER BY RANDOM() LIMIT :numberOfQuestions")
    List<Integer> findRandomQuestionIdsByCategory(String categoryName, Integer numberOfQuestions);

    @Query(value = "FROM Question q WHERE q.id IN :questionIds")
    List<Question> getQuestionFromIds(List<Integer> questionIds);

    @Query(value = "SELECT q.rightAnswer FROM Question q WHERE q.id = :id")
    String findRightAnsForId(Integer id);
}
