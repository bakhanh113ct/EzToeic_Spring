package com.alibou.security.Test.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmitTestBody {
    private List<Integer> parts;
    private String time; //milliseconds
    private List<SelectedAnswer> answers;

}