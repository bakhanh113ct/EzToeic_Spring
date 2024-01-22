package com.alibou.security.Test.models;

import com.alibou.security.question.QuestionView;

public interface TestView {
    public class Base {}

    public class HaveQuestion extends Base implements QuestionView {}
}
