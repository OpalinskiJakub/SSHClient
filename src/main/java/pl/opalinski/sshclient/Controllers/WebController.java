package pl.opalinski.sshclient.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WebController {
    @RequestMapping("/index")
    public String  index() {

        return "index.html";
    }
}
