package com.example.quizapp.controller;

import com.example.quizapp.entity.Quiz;
import com.example.quizapp.repository.QuizRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "*")
public class QuizController {
    private final QuizRepository quizRepository;

    public QuizController(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    // すべての質問を取得
    // @GetMapping
    // public List<Quiz> getAllQuizzes() {
    //     return quizRepository.findAll();
    // }

    // 新しい質問を追加
    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz savedQuiz = quizRepository.save(quiz);
        return ResponseEntity.ok(savedQuiz);
    }

    // 絞り込み用のAPIを追加
    @GetMapping
    public List<Quiz> getQuizzes(@RequestParam(required = false) String category) {
        if (category != null && !category.isEmpty()) {
            return quizRepository.findByCategory(category);
        } else {
            return quizRepository.findAll();
        }
    }
}
