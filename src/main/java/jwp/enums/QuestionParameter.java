package jwp.enums;

import java.sql.Timestamp;

public enum QuestionParameter {
    QUESTION_ID("questionId"),
    WRITER("writer"),
    TITLE("title"),
    CONTENTS("contents"),
    CREATED_DATE("createdDate"),
    COUNT_OF_ANSWER("countOfAnswer");

    private String parameter;

    QuestionParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return parameter;
    }
}
