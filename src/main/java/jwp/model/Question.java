package jwp.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp; // 생성 시간 자동화

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "QUESTIONS")
@NoArgsConstructor // JPA는 기본 생성자가 필요합니다.
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId; // ID는 Long 타입 권장

    private String writer;
    private String title;
    private String contents;

    @CreationTimestamp // INSERT 시 현재 시간을 자동으로 저장합니다.
    private Timestamp createdDate;

    private int countOfAnswer; // (답변 추가 시 +1 해주는 로직이 필요합니다)

    // Answer와의 1:N 관계
    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    // 서비스 로직에서 사용할 생성자
    public Question(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.countOfAnswer = 0; // 최초 생성 시 답변 0개
    }

    // --- (Getter 메서드) ---
    public Long getQuestionId() { return questionId; }
    public String getWriter() { return writer; }
    public String getTitle() { return title; }
    public String getContents() { return contents; }
    public Timestamp getCreatedDate() { return createdDate; }
    public int getCountOfAnswer() { return countOfAnswer; }
    public List<Answer> getAnswers() { return answers; }
}