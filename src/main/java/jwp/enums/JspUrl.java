package jwp.enums;

public enum JspUrl {
    HOME("home"),
    LIST("user/list"),
    LOGIN_FAIL("user/loginFailed"),
    UPDATE_FORM("user/updateForm"),
    QNA_FORM("qna/form"),
    QNA_SHOW("qna/show");

    private final String url;

    JspUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return url;
    }
}
