package jwp.utils;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    //로그인 여부 확인 메서드
    public static boolean isLogined(HttpSession session) {
        if (session == null) {
            return false;
        }

        //사용자 인증
        String id = (String) session.getAttribute("user");
        User targetUser = MemoryUserRepository.getInstance().findUserById(id);
        if (targetUser == null)
            return false;
        return true;

    }
}
