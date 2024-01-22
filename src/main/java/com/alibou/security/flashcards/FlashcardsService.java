package com.alibou.security.flashcards;

import com.alibou.security.flashcards.models.Flashcards;
import com.alibou.security.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FlashcardsService {
    private final FlashcardsRepository repository;

    public List<Flashcards> findAllFlashcards(Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        return repository.findAllByUser(user.getId());
    }

    public void saveFlashcards(Flashcards flashcards) {
        repository.save(flashcards);
    }
    public Optional<Flashcards> findOneById(int id){
        Optional<Flashcards> flashcards = repository.findById(id);
        return flashcards;
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
