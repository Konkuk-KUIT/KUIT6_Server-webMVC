package util;

import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    private SessionUtil() {}

    public static User getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return null;
        Object value = session.getAttribute("user");
        return (value instanceof User) ? (User) value : null;
    }

    public static boolean isLogin(HttpServletRequest request) {
        return getLoginUser(request) != null;
    }

    public static boolean isOwner(HttpServletRequest request, String targetUserId) {
        User loginUser = getLoginUser(request);
        return loginUser != null && loginUser.getUserId().equals(targetUserId);
    }
}
