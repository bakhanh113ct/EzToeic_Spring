package com.alibou.security.result.model;

import com.alibou.security.question.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultDetail {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id", referencedColumnName = "id")
    private Result result;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    private String answerByUser;
    private Boolean isCorrect;

}
