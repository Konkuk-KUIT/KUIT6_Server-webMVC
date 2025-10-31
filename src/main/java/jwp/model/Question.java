package jwp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Question {
    long questionId;
    String writer;
    String title;
    String contents;
    Timestamp createdDate;
    int countOfAnswer;

    public Question(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
        this.countOfAnswer = 0;
    }

    public Question(String writer, String title, String contents, Timestamp createdDate, int countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(long questionId, String writer, String title, String contents, Timestamp createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public long getQuestionId() {return questionId;}
    public String getWriter() {return writer;}
    public String getTitle() {return title;}
    public String getContents() {return contents;}
    public Timestamp getCreatedDate() {return createdDate;}
    public int getCountOfAnswer() {return countOfAnswer;}
}
