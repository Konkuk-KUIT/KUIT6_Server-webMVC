package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // POST 요청 시 한글 깨짐 방지
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        // UserSessionUtils를 사용하거나 직접 세션에서 User 객체를 가져옵니다.
        User sessionUser = (User) session.getAttribute("user");

        // 비로그인 사용자는 로그인 폼으로 리다이렉트
        if (sessionUser == null) {
            return "redirect:/user/loginForm";
        }

        // 폼에서 전송된 데이터
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        // writer는 폼 데이터가 아닌, 세션에서 가져온 ID를 사용 (보안)
        String writer = sessionUser.getUserId();

        // DAO를 통해 DB에 삽입
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(writer, title, contents);

        // 질문 생성 후 메인 페이지(질문 목록)로 리다이렉트
        return "redirect:/";
    }
}