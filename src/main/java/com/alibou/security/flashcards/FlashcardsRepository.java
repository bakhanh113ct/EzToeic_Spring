package com.alibou.security.flashcards;

import com.alibou.security.flashcards.models.Flashcards;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface FlashcardsRepository extends JpaRepository<Flashcards, Integer> {

    @Query("SELECT f FROM Flashcards f WHERE f.user.id = :id")
    List<Flashcards> findAllByUser(@Param("id") int userId);

    @Modifying
    @Query("DELETE FROM Flashcards f where f.id = :id")
    void deleteById(@Param("id") int id);
}
