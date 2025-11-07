package jwp.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "QUESTIONS")
@NoArgsConstructor // Lombok에서 제공하는 파라미터 없는 기본 생성자 자동 추가
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    private String writer;
    private String title;
    private String contents;

    @CreationTimestamp
    private Date createdDate;

    @Column(columnDefinition = "int default 0")
    private int countOfAnswer;

    public Question(long questionId, String writer, String title, String contents, Date createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(String writer, String title, String contents) {
        this.questionId = 0;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = new Date();
        this.countOfAnswer = 0;
    }

    public long getQuestionId() { return questionId; }
    public String getWriter() { return writer; }
    public String getTitle() { return title; }
    public String getContents() { return contents; }
    public Date getCreatedDate() { return createdDate; }
    public int getCountOfAnswer() { return countOfAnswer; }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", createdDate=" + createdDate +
                ", countOfAnswer=" + countOfAnswer +
                '}';
    }
}
