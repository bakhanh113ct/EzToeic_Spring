package com.alibou.security.Test.models;

import com.alibou.security.question.Question;
import com.alibou.security.result.model.Result;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonView(TestView.class)
public class Test {
    @Id
    @GeneratedValue
    @JsonView({TestView.Base.class, TestView.class})
    private Integer id;
    @JsonView({TestView.Base.class, TestView.class})
    private String title;
    @JsonView({TestView.Base.class, TestView.class})
    private int sectionCount;
    @JsonView({TestView.Base.class, TestView.class})
    private int questionCount;
    @JsonView({TestView.Base.class, TestView.class})
    private String time;

    @JsonIgnore
    @OneToMany(mappedBy = "test")
    private List<Result> results = new ArrayList<>();

//    @JsonIgnore
    @OneToMany(mappedBy = "test")
    @JsonView(TestView.HaveQuestion.class)
    private List<Question> questions = new ArrayList<>();

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    @JsonView({TestView.Base.class, TestView.class})
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    @JsonView({TestView.Base.class, TestView.class})
    private LocalDateTime updatedAt;

}
