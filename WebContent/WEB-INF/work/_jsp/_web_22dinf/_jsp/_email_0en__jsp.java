/*
 * JSP generated by Resin-3.1.12 (built Mon, 29 Aug 2011 03:22:08 PDT)
 */

package _jsp._web_22dinf._jsp;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;

public class _email_0en__jsp extends com.caucho.jsp.JavaPage
{
  private static final java.util.HashMap<String,java.lang.reflect.Method> _jsp_functionMap = new java.util.HashMap<String,java.lang.reflect.Method>();
  private boolean _caucho_isDead;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.WebApp _jsp_application = _caucho_getApplication();
    javax.servlet.ServletContext application = _jsp_application;
    com.caucho.jsp.PageContextImpl pageContext = _jsp_application.getJspApplicationContext().allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);
    javax.servlet.jsp.PageContext _jsp_parentContext = pageContext;
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    final javax.el.ELContext _jsp_env = pageContext.getELContext();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
    try {
      out.write(_jsp_string0, 0, _jsp_string0.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string1, 0, _jsp_string1.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string2, 0, _jsp_string2.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string3, 0, _jsp_string3.length);
      out.print((session.getId()));
      out.write(_jsp_string4, 0, _jsp_string4.length);
      out.print((session.getId()));
      out.write(_jsp_string5, 0, _jsp_string5.length);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      _jsp_application.getJspApplicationContext().freePageContext(pageContext);
    }
  }

  private java.util.ArrayList _caucho_depends = new java.util.ArrayList();

  public java.util.ArrayList _caucho_getDependList()
  {
    return _caucho_depends;
  }

  public void _caucho_addDepend(com.caucho.vfs.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;
    if (com.caucho.server.util.CauchoSystem.getVersionId() != 7170261747151080670L)
      return true;
    for (int i = _caucho_depends.size() - 1; i >= 0; i--) {
      com.caucho.vfs.Dependency depend;
      depend = (com.caucho.vfs.Dependency) _caucho_depends.get(i);
      if (depend.isModified())
        return true;
    }
    return false;
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public java.util.HashMap<String,java.lang.reflect.Method> _caucho_getFunctionMap()
  {
    return _jsp_functionMap;
  }

  public void init(ServletConfig config)
    throws ServletException
  {
    com.caucho.server.webapp.WebApp webApp
      = (com.caucho.server.webapp.WebApp) config.getServletContext();
    super.init(config);
    com.caucho.jsp.TaglibManager manager = webApp.getJspApplicationContext().getTaglibManager();
    com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.PageContextImpl(webApp, this);
    _caucho_expr_0 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${timestamp}");
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.server.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/jsp/email_en.jsp"), 5204102180469553725L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }
  private static com.caucho.el.Expr _caucho_expr_0;

  private final static char []_jsp_string5;
  private final static char []_jsp_string2;
  private final static char []_jsp_string0;
  private final static char []_jsp_string3;
  private final static char []_jsp_string1;
  private final static char []_jsp_string4;
  static {
    _jsp_string5 = "\";\r\n//                                alert(jsessionid2);\r\n                        var xmltext = htmlobj.responseText;\r\n\r\n//                                alert(xmltext);\r\n                        if (xmltext == 'Y') {\r\n\r\n                            var pro = $(\"#progressbar\");\r\n                            pro.progressbar(\"value\", 1);\r\n\r\n                            $(\"#progressbar\").show();\r\n\r\n                            return true;\r\n                        }\r\n\r\n                        else {\r\n                            $(\"#err5\").text(\"CAPTCHA error\");\r\n                            $(\"#kaptcha\").addClass(\"text_error\");\r\n                            return false;\r\n\r\n                        }\r\n                    }\r\n                }\r\n\r\n            }\r\n\r\n\r\n        }\r\n    }\r\n\r\n}\r\n\r\n\r\nfunction changeCode() {\r\n    $('#kaptchaImage').hide();\r\n    $('#kaptchaImage').attr(\r\n            'src',\r\n            './kaptcha/getKaptchaImage.do?'\r\n                    + Math.floor(Math.random() * 100));\r\n    $('#kaptchaImage').fadeIn();\r\n    event.cancelBubble = true;\r\n}\r\nfunction words_deal_name() {\r\n    var curLength = $(\"#name\").val().length;\r\n    if (curLength > 5) {\r\n        var num = $(\"#name\").val().substr(0, 20);\r\n        $(\"#name\").val(num);\r\n\r\n    }\r\n\r\n}\r\nfunction words_deal_subject() {\r\n    var curLength = $(\"#subject\").val().length;\r\n    if (curLength > 5) {\r\n        var num = $(\"#subject\").val().substr(0, 20);\r\n        $(\"#subject\").val(num);\r\n\r\n    }\r\n\r\n}\r\nfunction words_deal_email() {\r\n    if ($(\"#email\").val().length > 0 && $(\"#message\").val().length > 0) {\r\n        $(\"#send\").addClass(\"send_ok\");\r\n    }\r\n    else {\r\n        $(\"#send\").removeClass(\"send_ok\");\r\n    }\r\n    var curLength = $(\"#email\").val().length;\r\n    if (curLength > 50) {\r\n        var num = $(\"#email\").val().substr(0,50);\r\n        $(\"#email\").val(num);\r\n\r\n    }\r\n\r\n}\r\nfunction words_deal_message() {\r\n\r\n    if ($(\"#email\").val().length > 0 && $(\"#message\").val().length > 0) {\r\n        $(\"#send\").addClass(\"send_ok\");\r\n    }\r\n    else {\r\n        $(\"#send\").removeClass(\"send_ok\");\r\n    }\r\n    var curLength = $(\"#message\").val().length;\r\n    if (curLength > 200) {\r\n        var num = $(\"#message\").val().substr(0, 200);\r\n        $(\"#message\").val(num);\r\n\r\n    }\r\n\r\n}\r\n</script>\r\n\r\n</head>\r\n\r\n<body>\r\n<form method=\"post\" action=\"./addContact.do\" onsubmit=\"return validateForm()\">\r\n    <div id=\"progressbar\" class=\"ui-progressbar\"></div>\r\n    <h2>About</h2>\r\n    <p>Picoo Launcher is the fastest, smallest, and most power-saving phone launcher created on the market with Google Material Design. It is 100% faster than any default system launcher, and guarantees the best experience even on low configuration phones. More new features will be provided, and thank you for your support.</p>\r\n    <p>Drop us a line through <strong>picoolauncher@gmail.com</strong></p>\r\n    <div class=\"text_w\">\r\n        <p>\r\n            <input type=\"text\" class=\"text\" placeholder=\"Your Name\" name=\"name\" id=\"name\" oninput=\"words_deal_name();\"/>\r\n        </p>\r\n\r\n        <p id=\"err1\" class=\"error_tips\"></p>\r\n    </div>\r\n    <div class=\"text_w\">\r\n        <p>\r\n            <input type=\"text\" class=\"text\" placeholder=\"Your Email\" name=\"email\" id=\"email\"\r\n                   oninput=\"words_deal_email();\"/>\r\n            <em>(necessary)</em>\r\n        </p>\r\n\r\n        <p id=\"err2\" class=\"error_tips\">Your Email can not be empty</p>\r\n    </div>\r\n    <div class=\"text_w\">\r\n        <p>\r\n            <input type=\"text\" class=\"text\" placeholder=\"Subject\" id=\"subject\" name=\"subject\"\r\n                   oninput=\"words_deal_subject();\"/>\r\n        </p>\r\n\r\n        <p id=\"err3\" class=\"error_tips\"></p>\r\n    </div>\r\n    <div class=\"text_w\">\r\n        <input class=\"text\" placeholder=\"Message\" id=\"message\" name=\"message\"\r\n               oninput=\"words_deal_message();\"/>\r\n        <em>(necessary)</em>\r\n        <p id=\"err4\" class=\"error_tips\">Message can not be empty</p>\r\n    </div>\r\n    <div class=\"text_w\">\r\n        <p>\r\n            <input name=\"kaptcha\" type=\"text\" id=\"kaptcha\" class=\"text_k\" placeholder=\"CAPTCHA\">\r\n            <img src=\"./kaptcha/getKaptchaImage.do\" id=\"kaptchaImage\" style=\"top:10px;position: relative\"\r\n                 width=\"154\" height=\"35\"></p>\r\n\r\n        <p id=\"err5\" class=\"error_tips\">CAPTCHA can not be empty</p>\r\n    </div>\r\n    <div class=\"text_w\">\r\n        <p>\r\n            <input type=\"submit\" id=\"send\" class=\"send\" value=\"send\"></p>\r\n\r\n    </div>\r\n</form>\r\n</body>\r\n</html>".toCharArray();
    _jsp_string2 = "\"></script>\r\n<link rel=\"stylesheet\" href=\"http://rwww.picooapp.com/css/jquery-ui.css?ver=".toCharArray();
    _jsp_string0 = "\r\n\r\n<!DOCTYPE HTML>\r\n<html>\r\n<head>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">\r\n<title>\u53d1\u9001\u90ae\u4ef6</title>\r\n<link rel=\"stylesheet\" href=\"http://192.8.19.77/css/email.css\">\r\n<script type=\"text/javascript\"\r\n        src=\"http://rwww.picooapp.com/js/jquery-1.11.3.min.js?ver=".toCharArray();
    _jsp_string3 = "\"/>\r\n<style>\r\n\r\n    .ui-progressbar {\r\n        border: none;\r\n        background : #b0d0ef;\r\n        position: relative;\r\n        height: 5px;\r\n    }\r\n\r\n    .ui-progressbar-value{\r\n        background : #3d8bf2;\r\n    }\r\n</style>\r\n\r\n\r\n<script type=\"text/javascript\">\r\n$(function () {\r\n\r\n    $.ajaxSetup({cache: false });\r\n    var pro = $(\"#progressbar\");   //\u8fdb\u5ea6\u6761div\r\n\r\n    pro.progressbar({\r\n        value: false   //\u521d\u59cb\u5316\u7684\u503c\u4e3a0\r\n\r\n    });\r\n    setTimeout(addValue, 500);\r\n\r\n    //\u52a8\u6001\u4fee\u6539value\u7684\u51fd\u6570\r\n    function addValue() {\r\n        var pro = $(\"#progressbar\");\r\n        var newValue;\r\n        if (pro.progressbar(\"value\") > 0) {\r\n            newValue = pro.progressbar(\"value\") + 1;\r\n        }\r\n\r\n\r\n        pro.progressbar(\"value\", newValue); //\u8bbe\u7f6e\u65b0\u503c\r\n        if (newValue >= 95) {\r\n            return;\r\n        }    //\u8d85\u8fc7100\u65f6\uff0c\u8fd4\u56de\r\n\r\n        setTimeout(addValue, 20); //\u5ef6\u8fdf20\u6beb\u79d2\u9012\u5f52\u8c03\u7528\u81ea\u5df1\r\n    }\r\n\r\n    pro.hide();\r\n    $(\".error_tips\").text(\"\");\r\n    $(\"#kaptchaImage\")\r\n            .click(\r\n            function () {//\u751f\u6210\u9a8c\u8bc1\u7801\r\n                $(this).hide().attr(\r\n                                'src',\r\n                                './kaptcha/getKaptchaImage.do?'\r\n                                        + Math.floor(Math.random() * 100))\r\n                        .fadeIn();\r\n                event.cancelBubble = true;\r\n            });\r\n});\r\n\r\n//\u5ef6\u8fdf500\u6beb\u79d2\u8c03\u7528\u4fee\u6539value\u7684\u51fd\u6570\r\n\r\n\r\nfunction validateForm() {\r\n    $(\".text_error\").removeClass(\"text_error\");\r\n    $(\".error_tips\").text(\"\");\r\n    var verifyCodeValue = $(\"#kaptcha\").val();\r\n    var nameValue = $(\"#name\").val();\r\n    var subjectValue = $(\"#subject\").val();\r\n    var emailValue = $(\"#email\").val();\r\n    var messageValue = $(\"#message\").val();\r\n    if (nameValue.length > 40) {\r\n        $(\"#err1\").text(\"too long\");\r\n        $(\"#name\").addClass(\"text_error\");\r\n        return false;\r\n    }\r\n    else {\r\n\r\n        if (emailValue.replace(/\\s/g, \"\") == \"\") {\r\n            $(\"#err2\").text(\"Your Email can not be empty\");\r\n            $(\"#email\").addClass(\"text_error\");\r\n            return false;\r\n        }\r\n        else {\r\n            if (emailValue.length > 50) {\r\n                $(\"#err2\").text(\"too long\");\r\n                $(\"#email\").addClass(\"text_error\");\r\n                return false;\r\n            }\r\n            var reg = /^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$/;\r\n            isok = reg.test(emailValue);\r\n            if (!isok) {\r\n                $(\"#err2\").text(\"Invalid email address\");\r\n                $(\"#email\").addClass(\"text_error\");\r\n                return false;\r\n            }\r\n            else {\r\n\r\n                if (subjectValue.length > 40) {\r\n                    $(\"#err3\").text(\"too long\");\r\n                    $(\"#subject\").addClass(\"text_error\");\r\n                    return false;\r\n                }\r\n\r\n                else {\r\n\r\n                    if (messageValue.replace(/\\s/g, \"\") == \"\") {\r\n                        $(\"#err4\").text(\"Message can not be empty\");\r\n                        $(\"#message\").addClass(\"text_error\");\r\n                        return false;\r\n                    }\r\n                    if (messageValue.length > 200) {\r\n                        $(\"#err4\").text(\"message is too long\");\r\n                        $(\"#message\").addClass(\"text_error\");\r\n                        return false;\r\n                    }\r\n                    if (verifyCodeValue.replace(/\\s/g, \"\") == \"\") {\r\n                        $(\"#err5\").text(\"CAPTCHA can not be empty\");\r\n                        $(\"#kaptcha\").addClass(\"text_error\");\r\n                        return false;\r\n                    }\r\n\r\n                    else {\r\n                        //\u63d0\u4ea4\u524d\u5148\u5f02\u6b65\u68c0\u67e5\u9a8c\u8bc1\u7801\u662f\u5426\u8f93\u5165\u6b63\u786e\r\n\r\n                        var jsessionid = \"".toCharArray();
    _jsp_string1 = "\"></script>\r\n<script src=\"http://rwww.picooapp.com/js/jquery-ui.js?ver=".toCharArray();
    _jsp_string4 = "\";\r\n\r\n                        var verifyUrl = \"./check.do?verifyCode=\"\r\n                                + verifyCodeValue + \"&sessionid=\" + jsessionid;\r\n//                                alert(verifyUrl);\r\n                        var htmlobj = $.ajax({url: verifyUrl,\r\n                            async: false\r\n\r\n                        });\r\n                        var jsessionid2 = \"".toCharArray();
  }
}
