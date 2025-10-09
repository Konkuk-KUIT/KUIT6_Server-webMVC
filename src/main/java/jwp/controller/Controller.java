package jwp.controller;

import java.util.Map;

public interface Controller {
    public String process(Map<String, String> paramMap, Map<String, Object> model);
}
