package core.web.mvc;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private final Map<String, Controller> handlers = new HashMap<>();

    public void register(String path, Controller controller){ handlers.put(path, controller); }
    public Controller get(String path){ return handlers.get(path); }
}
