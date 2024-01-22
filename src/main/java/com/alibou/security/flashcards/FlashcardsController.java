package com.alibou.security.flashcards;


import com.alibou.security.flashcards.models.FlashcardRequest;
import com.alibou.security.flashcards.models.Flashcards;
import com.alibou.security.user.User;
import com.alibou.security.user.UserService;
import com.alibou.security.vocab.models.Vocab;
import com.alibou.security.vocab.VocabService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/api/v1/flashcards/lists")
public class FlashcardsController {
    private FlashcardsService flashcardsService;
    private VocabService vocabService;
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Flashcards>> findAllFlashcards(Principal connectedUser) {
        return ResponseEntity.ok(flashcardsService.findAllFlashcards(connectedUser));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<Vocab>> findAllVocabByFlashcard(@PathVariable int id) {
        return ResponseEntity.ok(vocabService.findAllVocabByFlashcard(id));
    }

    @PostMapping
    public ResponseEntity<?> createFlashcards(@RequestBody FlashcardRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        Optional<User> myUser = userService.findOneUserByEmail(user.getEmail());
        Flashcards flashcards =  Flashcards.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .user(myUser.get())
                .createdAt(LocalDateTime.now())
                .build();
        flashcardsService.saveFlashcards(flashcards);

        return ResponseEntity.accepted().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateFlashcards(@RequestBody FlashcardRequest request, @PathVariable int id) {
        Flashcards flashcards = flashcardsService.findOneById(id).get();
        flashcards.setTitle(request.getTitle());
        flashcards.setDescription(request.getDescription());

        flashcardsService.saveFlashcards(flashcards);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteFlashcards(@PathVariable int id) {
        List<Integer> ids = vocabService.findAllIdVocabByFlashcard(id);
        System.out.println(ids.stream().toList());
        vocabService.deleteAllVocab(ids);
        flashcardsService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
