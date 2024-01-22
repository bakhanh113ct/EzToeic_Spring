package com.alibou.security.flashcards.models;


import com.alibou.security.flashcards.FlashcardsView;
import com.alibou.security.user.User;
import com.alibou.security.vocab.models.Vocab;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonView(FlashcardsView.Base.class)
public class Flashcards {
    @Id
    @GeneratedValue
    @JsonView(FlashcardsView.Base.class)
    private Integer id;

    @JsonView(FlashcardsView.Base.class)
    private String title;
    @JsonView(FlashcardsView.Base.class)
    private String description;

    @OneToMany(mappedBy = "flashcards")
    @JsonView(FlashcardsView.Base.class)
    private List<Vocab> vocabs = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @JsonView(FlashcardsView.Base.class)
    private User user;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    @JsonView(FlashcardsView.Base.class)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    @JsonView(FlashcardsView.Base.class)
    private LocalDateTime updatedAt;
}
