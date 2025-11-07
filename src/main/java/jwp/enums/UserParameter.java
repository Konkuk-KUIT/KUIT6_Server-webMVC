package jwp.enums;

public enum UserParameter {
    USER_ID("userId"),
    PASSWORD("password"),
    NAME("name"),
    EMAIL("email");

    private final String parameter;

    UserParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getValue() {
        return parameter;
    }
}
