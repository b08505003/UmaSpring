package game.uma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/umas")
public class LogInController {

    @GetMapping("/login")
    public String login(){
        return "/manage/login";
    }

}
