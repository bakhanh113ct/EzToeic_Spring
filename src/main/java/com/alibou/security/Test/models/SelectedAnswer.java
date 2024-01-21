package com.alibou.security.Test.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SelectedAnswer {
    private Integer questionNumber;
    private String selected;
}