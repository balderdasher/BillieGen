package com.billiegen.common.servlet;

import com.billiegen.common.captcha.advance.Captcha;
import com.billiegen.common.captcha.advance.GifCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author CodePorter
 * @date 2017-10-27
 */
@WebServlet(name = "captchaServlet", urlPatterns = "/captcha.img")
public class CaptchaServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    public static final String SESSION_ATTR_CAPTCHA = "captcha";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/gif");
        String bgColor = request.getParameter("bg");
        bgColor = StringUtils.prependIfMissing(bgColor, "#");

        Captcha captcha = new GifCaptcha(125, 41, 4, bgColor);
        try {
            OutputStream out = response.getOutputStream();
            captcha.out(out);
            HttpSession session = request.getSession();
            session.setAttribute(SESSION_ATTR_CAPTCHA, captcha.text().toLowerCase());
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("exception happened when get captcha:{}", e.getMessage());
        }
    }
}

