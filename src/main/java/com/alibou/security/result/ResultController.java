package com.alibou.security.result;

import com.alibou.security.Test.models.TestView;
import com.alibou.security.result.model.Result;
import com.alibou.security.result.model.ResultView;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
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

    @GetMapping(path = "/results")
    @JsonView(ResultView.Base.class)
    public ResponseEntity<List<Result>> findAllResultByUser(Principal connectedUser){
        List<Result> results = service.finAllResultByUser(connectedUser);
        return ResponseEntity.ok(results);
    }

    @GetMapping(path = "/{testId}/results")
//    @JsonView(TestView.Base.class)
    @JsonView(ResultView.Base.class)
    public ResponseEntity<List<Result>> findAllResultByTest(@PathVariable int testId, Principal connectedUser) {
        List<Result> results = service.finAllResultByTest(testId, connectedUser);
//        SimpleBeanPropertyFilter simpleBeanPropertyFilter =
//                SimpleBeanPropertyFilter.serializeAllExcept("questions");
//
//        FilterProvider filterProvider = new SimpleFilterProvider()
//                .addFilter("userFilter", simpleBeanPropertyFilter);
//
//        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(results);
//        mappingJacksonValue.setFilters(filterProvider);
//        SimpleBeanPropertyFilter.serializeAllExcept("")
        return ResponseEntity.ok(results);
    }

    @GetMapping(path = "/results/{resultId}")
    @JsonView(ResultView.Detail.class)
    public ResponseEntity<Result> findResultById(@PathVariable int resultId, Principal connectedUser) {
        Result result = service.findById(resultId, connectedUser);
        return ResponseEntity.ok(result);
    }
}
