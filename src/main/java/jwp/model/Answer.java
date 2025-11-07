package jwp.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ANSWERS")
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId; // ID는 Long 타입 권장

    private String writer;
    private String contents;

    @CreationTimestamp
    private Timestamp createdDate;

    // Question과의 N:1 관계
    @ManyToOne
    @JoinColumn(name = "questionId") // DB의 외래키 컬럼명
    private Question question;

    // 서비스 로직에서 사용할 생성자
    public Answer(String writer, String contents, Question question) {
        this.writer = writer;
        this.contents = contents;
        this.question = question;
    }

    // --- (Getter 메서드) ---
    public Long getAnswerId() { return answerId; }
    public String getWriter() { return writer; }
    public String getContents() { return contents; }
    public Timestamp getCreatedDate() { return createdDate; }
    public Question getQuestion() { return question; }
}