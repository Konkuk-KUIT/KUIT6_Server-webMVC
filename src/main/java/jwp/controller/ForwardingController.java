package jwp.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForwardingController implements Controller {
    private String forwardPath = "";

    public ForwardingController(String forwardPath) {
        this.forwardPath = forwardPath;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return forwardPath;
    }
}
