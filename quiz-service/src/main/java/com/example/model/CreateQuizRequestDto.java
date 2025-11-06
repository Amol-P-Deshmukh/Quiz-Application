package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CreateQuizRequestDto {
    private String title;
    private String category;
    private Integer numOfQuestions;
}
