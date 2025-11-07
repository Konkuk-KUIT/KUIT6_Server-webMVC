package jwp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Table(name="QUESTIONS")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String questionId;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private int countOfAnswer;

    public Question(String questionId, String writer, String title, String contents, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(String questionId, String title, String contents, int countOfAnswer) {
        this.questionId = questionId;
        this.title = title;
        this.contents = contents;
        this.countOfAnswer = countOfAnswer;
    }

    public String getQuestionId() { return questionId; }
    public String getWriter() { return writer; }
    public String getTitle() { return title; }
    public String getContents() { return contents; }
    public int getCountOfAnswer() { return countOfAnswer; }

    public void updateTitleAndContents(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void increaseCountOfAnswer() {
        this.countOfAnswer+=1;
    }

    public void  decreaseCountOfAnswer() {
        this.countOfAnswer-=1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return getCountOfAnswer() == question.getCountOfAnswer() && Objects.equals(getWriter(), question.getWriter()) && Objects.equals(getTitle(), question.getTitle())&&Objects.equals(getContents(), question.getContents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestionId(), getWriter(), getTitle(), getContents());
    }

    public boolean isSameUser(User user) {
        if(user==null) return false;
        return writer.equals(user.getUserId());
    }
}
