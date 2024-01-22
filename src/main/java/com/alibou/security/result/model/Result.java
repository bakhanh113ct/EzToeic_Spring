package com.alibou.security.result.model;

import com.alibou.security.Test.models.Test;
import com.alibou.security.user.User;
import com.fasterxml.jackson.annotation.*;
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
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonView(ResultView.Base.class)
public class Result {
    @Id
    @GeneratedValue
    @JsonView(ResultView.Base.class)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonView(ResultView.Detail.class)
    @JoinColumn(name="test_id", referencedColumnName = "id")
    private Test test;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "result")
    @JsonIgnore
    private List<ResultDetail> resultDetails = new ArrayList<>();

    @JsonView(ResultView.Base.class)
    private String state;
    @JsonView(ResultView.Base.class)
    private String score;
    @JsonView(ResultView.Base.class)
    private int readingCorrectCount;
    @JsonView(ResultView.Base.class)
    private int correctCount;
    @JsonView(ResultView.Base.class)
    private int wrongCount;
    @JsonView(ResultView.Base.class)
    private int undoneCount;
    @JsonView(ResultView.Base.class)
    private String time;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    @JsonView(ResultView.Base.class)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    @JsonView(ResultView.Base.class)
    private LocalDateTime updatedAt;

}

