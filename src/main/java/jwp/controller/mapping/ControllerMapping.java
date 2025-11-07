package jwp.controller.mapping;

import jwp.controller.Controller;
public interface ControllerMapping {
    Controller mappingController(String uri);
}
