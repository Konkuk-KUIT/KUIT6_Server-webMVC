package jwp.model;

import java.util.Date;

public class Question {
    private long questionId;
    private String writer;
    private String title;
    private String contents;
    private Date createdDate;
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
