package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 모든 컨트롤러가 구현해야 하는 인터페이스
 * 요청을 처리하고 뷰 경로를 반환한다
 */
public interface Controller {
    /**
     * 요청을 처리하고 뷰 경로를 반환
     *
     * @param request HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @return 뷰 경로 (forward: "/경로", redirect: "redirect:/경로")
     * @throws Exception 처리 중 발생할 수 있는 예외
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
