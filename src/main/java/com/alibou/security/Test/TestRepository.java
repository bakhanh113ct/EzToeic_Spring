package com.alibou.security.Test;

import com.alibou.security.Test.models.QuestionAnswerByIndex;
import com.alibou.security.Test.models.Test;
import com.alibou.security.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
//    @Query("SELECT new com.alibou.security.Test.models.QuestionAnswerByIndex(q.index, q.answer) FROM Question q, Test t WHERE q.test.id = t.id AND t.id = :id")
    @Query("SELECT new com.alibou.security.Test.models.QuestionAnswerByIndex(q.index, q.answer) FROM Question q, Test t WHERE q.test.id = t.id AND t.id = :id")
    List<QuestionAnswerByIndex> findQuestionAnswersByTest(@Param("id") int id);

    @Query("SELECT q FROM Question q, Test t WHERE q.test.id = t.id AND t.id = :id")
    List<Question> findAllQuestionByTest(@Param("id") int id);

//    @Override
//    @Query("SELECT new com.alibou.security.Test.models.TestQuestionDTO(t.id, t.title, t.sectionCount, t.questionCount, t.time,t.questions, t.createdAt, t.updatedAt) FROM Test t WHERE  t.id = :id")
//    Optional<TestQuestionDTO> findWithQuestionById(@Param("id") Integer id);
}
