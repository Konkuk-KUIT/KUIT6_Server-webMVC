package jwp.enums;

public enum HttpUrl {
    ROOT(""),
    SIGNUP("user/signup"),
    LOGIN("user/login"),
    LOGOUT("user/logout"),
    LIST("user/list"),
    UPDATE_FORM("user/updateForm"),
    UPDATE("user/update"),
    QNA_FORM("qna/form"),
    QNA_CREATE("qna/create"),
    QNA_SHOW("qna/show");

    private final String url;

    HttpUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return url;
    }

    public String redirect() {
        return "redirect:/" + url;
    }

}
