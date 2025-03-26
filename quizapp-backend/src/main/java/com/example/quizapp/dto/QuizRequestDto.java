package com.example.quizapp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * クイズ登録用リクエストDTO（Lombokバージョン）
 * 入力値に対してバリデーションをかけています。
 */
@Data // getter/setter/toString/hashCode/equals すべて自動生成
@NoArgsConstructor // デフォルトコンストラクタ自動生成
public class QuizRequestDto {

    @NotBlank
    private String questionText;

    @NotBlank
    private String option1;

    @NotBlank
    private String option2;

    @NotBlank
    private String option3;

    @NotBlank
    private String option4;

    @Min(1)
    @Max(4)
    private Integer answer;

    @NotBlank
    private String explanation;

    @NotBlank
    private String category;
}
