package jwp.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "QUESTIONS")
@NoArgsConstructor
public class Question {
    @Id
    private long questionId;        // bigint -> long
    private String writer;          // varchar -> String
    private String title;           // varchar -> String
    private String contents;        // varchar -> String
    private Timestamp createdDate;  // timestamp -> java.sql.Timestamp
    private int countOfAnswer;      // int -> int

    public long getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public Question(String writer, String title, String contents) {
        this(0, writer, title, contents, new Timestamp(System.currentTimeMillis()), 0);
    }

    public Question(long questionId, String writer, String title, String contents, Timestamp createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Question that = (Question) obj;
        return this.questionId != 0 && this.questionId == that.questionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId);
    }
}