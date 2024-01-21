package com.alibou.security.Test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionAnswerByIndex {
    @JsonProperty("index")
    private Integer index;
    @JsonProperty("answer")
    private String answer;
}
