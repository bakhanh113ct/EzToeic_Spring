package com.alibou.security.result;

import com.alibou.security.result.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/api/v1/tests")
public class ResultController {
    private final ResultService service;

    @GetMapping(path = "/{testId}/results")
    public ResponseEntity<List<Result>> findAllResultByUser(@PathVariable int testId, Principal connectedUser) {
//        List<Result> results = ;


        return ResponseEntity.ok(service.finAllResultByTest(testId, connectedUser));
    }
}
