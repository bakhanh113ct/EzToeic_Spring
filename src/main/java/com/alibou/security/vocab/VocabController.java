package com.alibou.security.vocab;

import com.alibou.security.flashcards.FlashcardsService;
import com.alibou.security.flashcards.models.Flashcards;
import com.alibou.security.vocab.models.Vocab;
import com.alibou.security.vocab.models.VocabRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/flashcards/vocabs")
@RequiredArgsConstructor
public class VocabController {
    private final VocabService vocabService;
    private final FlashcardsService flashcardsService;

    @PostMapping()
    public ResponseEntity<?> createVocab(@RequestBody VocabRequest request) {
        Optional<Flashcards> flashcards = flashcardsService.findOneById(request.getFlashcardId());
        Vocab vocab = Vocab.builder()
                .vocabulary(request.getVocabulary())
                .definition(request.getDefinition())
                .flashcards(flashcards.get())
                .createdAt(LocalDateTime.now())
                .build();
        vocabService.saveVocab(vocab);
        return ResponseEntity.accepted().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateVocab(@RequestBody VocabRequest request, @PathVariable int id) {
//        Optional<Flashcards> flashcards = flashcardsService.findOneById(request.getFlashcardId());
        Vocab vocab = vocabService.findById(id).get();

        vocab.setVocabulary(request.getVocabulary());
        vocab.setDefinition(request.getDefinition());

        vocabService.saveVocab(vocab);

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteVocab(@PathVariable int id) {

        vocabService.deleteVocab(id);

        return ResponseEntity.accepted().build();

    }
}
