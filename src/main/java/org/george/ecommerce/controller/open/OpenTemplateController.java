package org.george.ecommerce.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OpenTemplateController {

    @GetMapping("login")
    public String loginView() {
        return "login";
    }

    @GetMapping("main")
    public String mainView() {
        return "main";
    }
}
