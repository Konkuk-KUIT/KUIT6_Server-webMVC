package jwp.enums;

public enum JspUrl {
    HOME("/home.jsp"),
    LIST("/user/list.jsp"),
    LOGIN_FAIL("/user/loginFailed.jsp"),
    UPDATE_FORM("/user/updateForm.jsp"),
    QNA_FORM("/qna/form.jsp"),
    QNA_SHOW("/qna/show.jsp");

    private final String url;

    JspUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return url;
    }
}
