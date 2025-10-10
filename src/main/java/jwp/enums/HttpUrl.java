package jwp.enums;

public enum HttpUrl {
    ROOT("/"),
    REDIRECT("redirect:/"),
    SIGNUP("/user/signup"),
    LOGIN("/user/login"),
    LOGOUT("/user/logout"),
    LIST("/user/list"),
    UPDATE_FORM("/user/updateForm"),
    UPDATE("/user/update");

    private final String url;

    HttpUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return url;
    }

}
