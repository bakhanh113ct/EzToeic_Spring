package com.alibou.security.result;

import com.alibou.security.result.model.ResultDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultDetailRepository extends JpaRepository<ResultDetail, Integer> {

}
