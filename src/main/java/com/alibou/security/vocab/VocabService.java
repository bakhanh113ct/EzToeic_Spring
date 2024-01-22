package com.alibou.security.vocab;

import com.alibou.security.vocab.models.Vocab;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VocabService {
    private VocabRepository repository;

    public List<Vocab> findAllVocabByFlashcard(int id) {
        return repository.findAllByFlashcard(id);
    }

    public void saveVocab(Vocab request) {
        repository.save(request);
    }

    public Optional<Vocab> findById(int id) {
        return repository.findById(id);
    }

    public void deleteVocab(int id) {
        repository.deleteById(id);
    }

    public void deleteAllVocab(List<Integer> ids) {
        repository.deleteAllById(ids);
    }

    public List<Integer> findAllIdVocabByFlashcard(int id) {
        return repository.findAllIdByFlashcard(id);
    }
}
