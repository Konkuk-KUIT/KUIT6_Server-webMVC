package core.mvc;

import java.util.HashMap;
import java.util.Map;

/**
 * URL 패턴과 컨트롤러를 매핑하는 클래스
 * 요청 URL에 따라 적절한 컨트롤러를 반환한다
 */
public class RequestMapper {
    private Map<String, Controller> mappings = new HashMap<>();

    /**
     * URL 패턴과 컨트롤러를 매핑
     *
     * @param urlPattern URL 패턴 (예: "/user/login")
     * @param controller 처리할 컨트롤러
     */
    public void addMapping(String urlPattern, Controller controller) {
        mappings.put(urlPattern, controller);
    }

    /**
     * URL에 해당하는 컨트롤러를 반환
     *
     * @param url 요청 URL
     * @return 해당하는 컨트롤러, 없으면 null
     */
    public Controller getController(String url) {
        return mappings.get(url);
    }
}
