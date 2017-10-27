package com.example.front;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CodePorter
 * @date 2017-10-27
 */
@RestController
@RequestMapping("${billie.front.path}")
public class FrontAction {

    @RequestMapping("/front")
    public String welcomeFront() {
        return "Welcome to front";
    }
}
