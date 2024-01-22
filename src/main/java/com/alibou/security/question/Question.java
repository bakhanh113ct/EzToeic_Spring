package com.alibou.security.question;

import com.alibou.security.Test.models.Test;
import com.alibou.security.part.Part;
import com.alibou.security.result.model.Result;
import com.alibou.security.result.model.ResultDetail;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
//@ToString
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonView(QuestionView.class)
public class Question {
    @Id
    @GeneratedValue
    private Integer id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test test;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private Part part;

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    private List<ResultDetail> resultDetails = new ArrayList<>();

    @JsonProperty("index")
    private Integer index;
    private String question;
    @JsonProperty("answer")
    private String answer;
    private String imageUrl;
    private String audioUrl;
    private String A;
    private String B;
    private String C;
    private String D;

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
