package com.nq.picco.controller;

import java.io.IOException;
import java.io.PrintWriter;
import com.nq.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.code.kaptcha.Constants;
@Controller
public class VerifyController {
    static Logger logger = Logger.getLogger(CaptchaController.class);
	@RequestMapping("/check")     
	public void verify(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "sessionid", required = true) String sessionid,@RequestParam(value = "verifyCode", required = true) String kaptchaReceived) throws IOException {

        String resultData = null;
		PrintWriter out = response.getWriter();

	    try {
	    	logger.info("verify");
	        //��Ӧ���
	        
	        //��ȡ����������֤��
	        String verifyCode = kaptchaReceived;

	        if(verifyCode=="") {
	            resultData = "N";
	        }
	        else {
                MySessionContext myc= MySessionContext.getInstance();
                HttpSession session = myc.getSession(sessionid);
                String kaptchaValue = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
                System.out.println(kaptchaValue);
	            if(kaptchaValue == null || kaptchaValue == ""||!verifyCode.equalsIgnoreCase(kaptchaValue)) {
	                resultData = "N";
                    session.setAttribute("result",new String("N"));

	            }else {
	                resultData = "Y";
                    session.setAttribute("result",new String("Y"));
	            }
	        }
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	    }
        MySessionContext myc= MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionid);
       logger.info(resultData);
	    out.write(resultData);

    }
}
	
