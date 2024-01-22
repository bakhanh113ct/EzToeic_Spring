package com.alibou.security.result.model;

import com.alibou.security.Test.models.TestView;

public interface ResultView {
    public class Base {}
    public class Detail extends Base implements TestView {}
}
