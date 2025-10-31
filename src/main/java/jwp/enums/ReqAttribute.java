package jwp.enums;

public enum ReqAttribute {
    QUESTIONS("questions"),
    QUESTION("question"),
    USERS("users"),
    USER("user");

    private String attribute;

    ReqAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getValue() {
        return attribute;
    }
}
