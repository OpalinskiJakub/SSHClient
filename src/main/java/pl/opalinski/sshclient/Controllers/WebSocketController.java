package pl.opalinski.sshclient.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WebSocketController {
    @RequestMapping("/terminal")
    public String showTerminalPage() {
        return "terminalPage.html";
    }

    @RequestMapping("/start")
    public String showStartPage() {
        return "startPage.html";
    }

    @RequestMapping("/createAccountPage")
    public String showCreateAccountPage() {
        return "createAccountPage.html";
    }

}
