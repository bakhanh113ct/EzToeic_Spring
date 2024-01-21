package com.alibou.security.result;

import com.alibou.security.result.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    @Query("SELECT r FROM Result r WHERE r.user.id = :userId AND r.test.id = :testId")
    List<Result> findResultsByUser(@Param("userId") int userId, @Param("testId") int testId);
}
