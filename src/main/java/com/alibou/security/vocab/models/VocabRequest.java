package com.alibou.security.vocab.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VocabRequest {
    private Integer flashcardId;
    private String vocabulary;
    private String definition;
}
