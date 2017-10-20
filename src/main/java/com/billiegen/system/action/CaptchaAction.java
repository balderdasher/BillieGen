package com.billiegen.system.action;

import com.billiegen.common.captcha.advance.Captcha;
import com.billiegen.common.captcha.advance.GifCaptcha;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author CodePorter
 * @date 2017-10-20
 */
@Controller
public class CaptchaAction {
    private static final Logger logger = LogManager.getLogger();
    public static final String SESSION_ATTR_CAPTCHA = "captcha";

    @GetMapping(value = "/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/gif");

        Captcha captcha = new GifCaptcha(146, 33, 5);
        try {
            OutputStream out = response.getOutputStream();
            captcha.out(out);
            HttpSession session = request.getSession(true);
            session.setAttribute(SESSION_ATTR_CAPTCHA, captcha.text().toLowerCase());
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("exception happened when get captcha:{}", e.getMessage());
        }
    }
}
