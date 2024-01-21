package com.alibou.security.Test;

import com.alibou.security.Test.models.QuestionAnswerByIndex;
import com.alibou.security.Test.models.SelectedAnswer;
import com.alibou.security.Test.models.SubmitTestBody;
import com.alibou.security.Test.models.Test;
import com.alibou.security.question.Question;
import com.alibou.security.result.model.Result;
import com.alibou.security.result.ResultService;
import com.alibou.security.result.model.ResultDetail;
import com.alibou.security.user.User;
import com.alibou.security.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    private final ResultService resultService;
    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<Test>> finalAllTests(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        Pageable firstPageWithTwoElements = PageRequest.of(page, 1);
        Page<Test> pagedResult = testService.findAll(firstPageWithTwoElements);

        if (pagedResult.hasContent()) {
            return ResponseEntity.ok(pagedResult.getContent());
        } else {
            return ResponseEntity.ok(new ArrayList<Test>());
        }
//        return ResponseEntity.ok(service.findAll(firstPageWithTwoElements));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Test>> findById(@PathVariable int id) {
        return ResponseEntity.ok(testService.findById(id));
    }

    @GetMapping(path = "/{id}/solution")
    public ResponseEntity<List<QuestionAnswerByIndex>> findSolutionByTestId(@PathVariable int id) {
        List<QuestionAnswerByIndex> solution = testService.findSolutionByTestId(id);

        return ResponseEntity.ok(solution.stream().toList());
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<?> submitTest(@PathVariable int id, @RequestBody SubmitTestBody request, Principal connectedUser) {
        Optional<Test> test = testService.findById(id);
        int correctCount = 0;
        int wrongCount = 0;
        if (test.isPresent()) {
            List<Question> questions = testService.findAllQuestionByTest(id);
            Map<Integer, String> hashMap = questions.stream()
                    .collect(Collectors.toMap(Question::getIndex, Question::getAnswer));

            var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
            Optional<User> myUser = userService.findOneUserByEmail(user.getEmail());
            Result result = Result.builder().user(myUser.get()).test(test.get()).build();
            resultService.saveResult(result);

//            System.out.println(hashMap.get(1));
            for (SelectedAnswer answer : request.getAnswers()) {
//                System.out.println(name);
                Boolean isCorrect = false;
                if (hashMap.containsKey(answer.getQuestionNumber()) &&
                        Objects.equals(hashMap.get(answer.getQuestionNumber()),
                                answer.getSelected())) {
                    correctCount++;
                    isCorrect = true;
                } else if (hashMap.get(answer.getQuestionNumber()) != null &&
                        !Objects.equals(hashMap.get(answer.getQuestionNumber()),
                                answer.getSelected())) {
                    wrongCount++;
                }
                System.out.println(hashMap.get(answer.getQuestionNumber()));
//                result.getResultsDetail().add(ques)
                ResultDetail resultDetail = ResultDetail.builder().result(result)
                        .question(questions.stream().filter(q -> Objects.equals(q.getIndex(), answer.getQuestionNumber())).findFirst().get())
                        .answerByUser(answer.getSelected()).isCorrect(isCorrect).build();
                resultService.saveResultDetail(resultDetail);

                ///TODO Apply transaction to rollback when have any error - Currently just handle in the good situation (Data full question)
            }

            System.out.println(correctCount);
            System.out.println(wrongCount);


        }
//        System.out.println(request.getTime());
//        System.out.println(request.getParts());
//        System.out.println(request.getAnswers().get(0));
        return ResponseEntity.accepted().build();
    }
}
