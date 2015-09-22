package com.nq.picco.controller;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;  
import javax.servlet.ServletOutputStream;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;  
import com.nq.util.*;
/** 
 * ��ֹCaptcha�����˵�½ 
 * @ author liuwang 
 * 
 */  
@ Controller  
@ RequestMapping("/kaptcha/*")  
public class CaptchaController {
    static Logger logger = Logger.getLogger(CaptchaController.class);
    @ Autowired  
    private Producer captchaProducer = null;  

    @ RequestMapping  
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MySessionContext myc= MySessionContext.getInstance();
        myc.AddSession(request.getSession());
        HttpSession session = myc.getSession(request.getSession().getId());

        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);  
       logger.info("create captcha");

        response.setDateHeader("Expires", 0);  

        // Set standard HTTP/1.1 no-cache headers.  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  

        // Set standard HTTP/1.0 no-cache header.  
        response.setHeader("Pragma", "no-cache");  

        // return a jpeg  
        response.setContentType("image/jpeg");  

        // create the text for the image  
        String capText = captchaProducer.createText();  

        // store the text in the session  
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);  

        // create the image with the text  
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();

        ImageIO.write(bi, "jpg", out);
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
    }  

}  
