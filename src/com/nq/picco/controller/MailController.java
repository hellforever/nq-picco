package com.nq.picco.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

import com.nq.picco.DAO.MailDAO;
import com.nq.picco.model.Contact;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nq.util.MD5;

@Controller
public class MailController {
	static Logger logger = Logger.getLogger(MailController.class);
	private static final MailDAO mailsender = new MailDAO();
	@RequestMapping(value = "/addContact")
	public String addContact(
			HttpServletRequest request,
			@RequestParam(value = "kaptcha", required = true) String kaptchaReceived,
			@RequestParam(value = "name", required = true) String nameReceived,
			@RequestParam(value = "subject", required = true) String subjectReceived,
			@RequestParam(value = "email", required = true) String emailReceived,
			@RequestParam(value = "message", required = true) String messageReceived) {

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("mail.properties");
        Properties p = new Properties();
        try {
            logger.info("load mail.properties");
            p.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }


        // 获取验证码
		String kaptchaExpected = (String) request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		// 验证码判断
		if (kaptchaReceived == null
				|| !(kaptchaReceived.equals(kaptchaExpected))) {
			return "redirect:error.do";// 返回
		}


		String requestUrl = null;
		requestUrl = p.getProperty("EMAIL_PROD_QUEST_URL");
		List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		list.add(new BasicNameValuePair("toAddress", "chenting@nq.com"));
		list.add(new BasicNameValuePair("ccAddress", ""));
		list.add(new BasicNameValuePair("bccAddress", ""));
		list.add(new BasicNameValuePair("mailSubject", subjectReceived
				+ "from:" + emailReceived));
		list.add(new BasicNameValuePair("mailContent", messageReceived));
		list.add(new BasicNameValuePair("productName", p.getProperty("EMAIL_PRODUCT_NAME")));

		// MD5加密
		String paramStr = URLEncodedUtils.format(list, "UTF-8");
		String md5 = MD5.getMD5ofStr(paramStr + p.getProperty("EAMIL_MD5_KEY"));

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(requestUrl + paramStr + "&signature="
				+ md5);
		try {
            String path = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
            String  url  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);

            if(request.getQueryString()!=null)
            {
                url+="?"+request.getQueryString();
            }
            System.out.println("path："+path);
            System.out.println("basePath："+basePath);
            System.out.println("URL："+url);
            System.out.println("URL参数："+request.getQueryString());
            HttpResponse response = httpClient.execute(httpGet);

			int statusCode = response.getStatusLine().getStatusCode();
            if( statusCode == 200)
                logger.info("send mail");
            else
                logger.info("send mail fail");
			Contact contact = new Contact();
			contact.setEmail(emailReceived);
			contact.setMessage(messageReceived);
			contact.setName(nameReceived);
			contact.setSubject(subjectReceived);
			mailsender.SaveMail(contact );
            logger.info("insert into picco_web_Email (name, subject, mail, message) values"+contact.getName()+" "+contact.getEmail()+" "+contact.getSubject()+" "+contact.getMessage());
			return "redirect:success.do";
		} catch (Exception e) {
            logger.info("save DB fail");
			e.printStackTrace();

			// 返回错误页面
			return "redirect:error.do";
		} finally {
			httpGet.abort();
			httpClient.getConnectionManager().shutdown();

		}
//        // Recipient's email ID needs to be mentioned.
//        String to = "chenting@nq.com";//change accordingly
//
//        // Sender's email ID needs to be mentioned
//        String from = "517441070@qq.com";//change accordingly
//        final String username = "517441070@qq.com";//change accordingly
//        final String password = "ct6057236";//change accordingly
//
//        // Assuming you are sending email through relay.jangosmtp.net
//        String host = "smtp.qq.com";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "25");
//
//        // Get the Session object.
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//            // Create a default MimeMessage object.
//            Message message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(to));
//
//            // Set Subject: header field
//            message.setSubject("Testing Subject");
//
//            // Now set the actual message
//            message.setText("Hello, this is sample for to check send "
//                    + "email using JavaMailAPI ");
//
//            // Send message
//            Transport.send(message);
//
//            System.out.println("Sent message successfully....");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//        Contact contact = new Contact();
//        contact.setEmail(emailReceived);
//        contact.setMessage(messageReceived);
//        contact.setName(nameReceived);
//        contact.setSubject(subjectReceived);
//        mailsender.SaveMail(contact );
//        System.out.println("发送邮件");
//        return "redirect:success.do";

	}
	@RequestMapping("/success")
	public ModelAndView showSuccess() {
		return new ModelAndView("success");
	}
    @RequestMapping("/cn")
    public ModelAndView showEmail(HttpServletResponse response) {

        response.setHeader("P3P", "CP=CAO PSA OUR");
        return new ModelAndView("email","timestamp",new Date());
    }
    @RequestMapping("/testProgress")
    public ModelAndView show(HttpServletResponse response) {

        response.setHeader("P3P", "CP=CAO PSA OUR");
        return new ModelAndView("testProgress","timestamp",new Date());
    }
    @RequestMapping("/en")
    public ModelAndView showEmail_en(HttpServletResponse response) {

        response.setHeader("P3P","CP=CAO PSA OUR");
        return new ModelAndView("email_en","timestamp",new Date());
    }
	@RequestMapping("/contacts")
	public ModelAndView showContacts() {
	
		return new ModelAndView("contact","command",new Contact());
	}

	@RequestMapping("/kaptcha")
	public ModelAndView showKaptcha() {
		return new ModelAndView("kaptcha");
	}
    @RequestMapping("/error")
    public ModelAndView showError() {
        return new ModelAndView("error");
    }

}
