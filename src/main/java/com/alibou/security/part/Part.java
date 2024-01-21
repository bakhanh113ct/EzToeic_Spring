package com.alibou.security.part;

import com.alibou.security.question.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Part {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(mappedBy = "part")
    private Set<Question> questions = new HashSet<>();

    private int number;
    private String introduction;
    private int questionCount;
    private int selectAnswerCount;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
