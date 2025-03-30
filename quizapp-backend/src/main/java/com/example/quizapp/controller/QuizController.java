package com.example.quizapp.controller;

import com.example.quizapp.entity.Quiz;
import com.example.quizapp.repository.QuizRepository;
import com.example.quizapp.dto.QuizRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "http://localhost:5173")
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

    // 絞り込み用のAPIを追加
    @GetMapping
    public List<Quiz> getQuizzes(@RequestParam(required = false) String category) {
        if (category != null && !category.isEmpty()) {
            return quizRepository.findByCategory(category);
        } else {
            return quizRepository.findAll();
        }
    }

    // クイズの詳細を取得するAPIを追加
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        System.out.println("ID: " + id);
        return quizRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // クイズを投稿するAPIを追加
    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody QuizRequestDto quizDto) {
        Quiz quiz = new Quiz();
        quiz.setQuestionText(quizDto.getQuestionText());
        quiz.setOption1(quizDto.getOption1());
        quiz.setOption2(quizDto.getOption2());
        quiz.setOption3(quizDto.getOption3());
        quiz.setOption4(quizDto.getOption4());
        quiz.setAnswer(quizDto.getAnswer());
        quiz.setExplanation(quizDto.getExplanation());
        quiz.setCategory(quizDto.getCategory());

        Quiz savedQuiz = quizRepository.save(quiz);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuiz);
    }

    // クイズを削除するAPIを追加
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        if (!quizRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        quizRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
