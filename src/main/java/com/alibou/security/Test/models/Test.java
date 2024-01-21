package com.alibou.security.Test.models;

import com.alibou.security.question.Question;
import com.alibou.security.result.model.Result;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
//@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@ToString
public class Test {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private int sectionCount;
    private int questionCount;
    private String time;

    @JsonIgnore
    @OneToMany(mappedBy = "test")
    private List<Result> results = new ArrayList<>();

    @OneToMany(mappedBy = "test")
    private List<Question> questions = new ArrayList<>();

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
