package jwp.model;

import java.sql.Timestamp;

public class Question {
    //    questionId 			bigint				auto_increment,
//    writer				varchar(30)			NOT NULL,
//    title				varchar(50)			NOT NULL,
//    contents			varchar(5000)		NOT NULL,
//    createdDate			timestamp			NOT NULL,
//    countOfAnswer int,
//    PRIMARY KEY               (questionId)
    private long questionId;
    private String writer;
    private String title;
    private String contents;
    private Timestamp createdDate;
    private int countOfAnswer;

    public Question(long questionId, String writer, String title, String contents, Timestamp createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(String writer, String title, String contents) {
        this(0, writer, title, contents, new Timestamp(System.currentTimeMillis()), 0);
    }

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
}
