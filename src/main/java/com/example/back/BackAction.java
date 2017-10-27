package com.example.back;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodePorter
 * @date 2017-10-27
 */
@RestController
@RequestMapping("${billie.back.path}")
public class BackAction {

    @RequestMapping("/back")
    public String welcomeBack() {
        return "Welcome to back";
    }
}
