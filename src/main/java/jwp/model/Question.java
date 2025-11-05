package jwp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "QUESTIONS")
@NoArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private Integer countOfAnswer;

    public Question(Long questionId, String writer, String title, String contents, LocalDateTime createdDate, Integer countOfAnswers) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswers;
    }

    @PrePersist
    public void prePersist() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        return "Question [questionId=" + questionId +
                ", writer=" + writer +
                ", title=" + title +
                ", createdDate=" + createdDate +
                ", countOfAnswer=" + countOfAnswer + "]";
    }
}
