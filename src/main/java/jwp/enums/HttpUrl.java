package jwp.enums;

public enum HttpUrl {
    ROOT("/"),
    REDIRECT("redirect:/"),
    SIGNUP("/user/signup"),
    LOGIN("/user/login"),
    LOGOUT("/user/logout"),
    LIST("/user/list"),
    UPDATE_FORM("/user/updateForm"),
    UPDATE("/user/update"),
    QNA_FORM("/qna/form.html"),
    QNA_CREATE("/qna/create"),
    QNA_SHOW("/qna/show");

    private final String url;

    HttpUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return url;
    }

}
