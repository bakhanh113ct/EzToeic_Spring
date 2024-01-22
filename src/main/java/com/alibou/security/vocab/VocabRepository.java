package com.alibou.security.vocab;

import com.alibou.security.vocab.models.Vocab;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface VocabRepository extends JpaRepository<Vocab, Integer> {
    @Query("SELECT v FROM Vocab v WHERE v.flashcards.id = :id")
    List<Vocab> findAllByFlashcard(@Param("id") int id);

    @Modifying
    @Query("DELETE FROM Vocab v where v.id=:id")
    void deleteById(@Param("id") int id);

    @Query("SELECT v.id FROM Vocab v WHERE v.flashcards.id = :id")
    List<Integer> findAllIdByFlashcard(@Param("id") int id);

    @Modifying
    @Query("DELETE FROM Vocab v WHERE v.id IN :ids")
    void deleteAllById(@Param("ids") List<Integer> ids);
}
