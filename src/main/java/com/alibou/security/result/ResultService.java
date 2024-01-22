package com.alibou.security.result;

import com.alibou.security.result.model.Result;
import com.alibou.security.result.model.ResultDetail;
import com.alibou.security.user.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;
    private final ResultDetailRepository resultDetailRepository;

    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    public Result findById(int id, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        return resultRepository.findById(id).get();
    }

    public List<Result> finAllResultByTest(int id, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        //        System.out.println(user.getEmail());
        return resultRepository.findResultsByTest(user.getId(), id);
    }

    @Transactional
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    public ResultDetail saveResultDetail(ResultDetail resultDetail) {
        return resultDetailRepository.save(resultDetail);
    }

    public List<Result> finAllResultByUser(Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        return resultRepository.findResultsByUser(user.getId());
    }
}
