package com.alibou.security.result.model;

import com.alibou.security.Test.models.Test;
import com.alibou.security.question.Question;
import com.alibou.security.user.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Result {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JsonIgnore
    @JoinColumn(name="test_id", referencedColumnName = "id")
    private Test test;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "result")
    @JsonIgnore
    private List<ResultDetail> resultDetails = new ArrayList<>();

    private String state;
    private String score;
    private int readingCorrectCount;
    private int correctCount;
    private int wrongCount;
    private int undoneCount;
    private String time;

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

