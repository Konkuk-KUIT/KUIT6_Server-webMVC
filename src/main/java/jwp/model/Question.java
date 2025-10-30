package jwp.model;

import java.time.LocalDateTime;


public class Question {
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
    public Question(String writer, String title, String contents) {
        // 폼에서 받은 3개 필드 값 할당
        this.writer = writer;
        this.title = title;
        this.contents = contents;

        this.questionId = null; // ID는 DB가 자동 생성하므로 0L로 임시 초기화 (long 타입에 맞춤)
        this.createdDate = LocalDateTime.now();
        this.countOfAnswer = 0; // 답변 개수는 0으로 초기화
    }

    public Integer getCountOfAnswer() {
        return countOfAnswer;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public java.sql.Timestamp getCreatedDateAsTimestamp() {
        // LocalDateTime을 JDBC Timestamp로 변환
        return java.sql.Timestamp.valueOf(this.createdDate);
    }

    public String getContents() {
        return contents;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public Long getQuestionId() {
        return questionId;
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
