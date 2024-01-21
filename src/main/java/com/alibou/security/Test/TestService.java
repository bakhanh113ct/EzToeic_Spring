package com.alibou.security.Test;

import com.alibou.security.Test.models.QuestionAnswerByIndex;
import com.alibou.security.Test.models.Test;
import com.alibou.security.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository repository;

    public Page<Test> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public Optional<Test> findById(int id) {
        return repository.findById(id);
    }

    public List<QuestionAnswerByIndex> findSolutionByTestId(int id) {
        return repository.findQuestionAnswersByTest(id);
    }

    public List<Question> findAllQuestionByTest(int id) {
        return repository.findAllQuestionByTest(id);
    }
    
//    public Result save(Result result) {
//        return repository.save(result);
//    }
}
