package com.mrdios.example.back;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CodePorter
 * @date 2017-10-27
 */
@Controller
@RequestMapping("${billie.back.path}")
public class BackAction {

    @RequestMapping("/welcome")
    public String welcomeFront(Model model) {
        model.addAttribute("msg", "Welcome to back");
        return "index_back";
    }

    @RequestMapping("/welcome.json")
    @ResponseBody
    public String welcome() {
        return "Welcome to back";
    }
}
