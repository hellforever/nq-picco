<span style="font-size:18px;">
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs
/jquery/1.4.0/jquery.min.js"></script>
  
  
<title>测试页面</title>  
<script type="text/javascript">  
$(function(){         
    $('#kaptchaImage').click(function () {//生成验证码  
     $(this).hide().attr('src', './kaptcha/getKaptchaImage.do?' + Math.floor(Math.random()*100) ).fadeIn();  
     event.cancelBubble=true;  
    });  
});   

  
function changeCode() {
    $('#kaptchaImage').hide();
    $('#kaptchaImage').attr('src', './kaptcha/getKaptchaImage.do?' + Math.floor(Math.random()*100) );
    $('#kaptchaImage').fadeIn();  
    event.cancelBubble=true;  
}  
</script>  
</head>  
<body>  
          
<div class="chknumber">  
      <label>验证码:  
      <input name="kaptcha" type="text" id="kaptcha" maxlength="4" class="chknumber_input" />               
      </label>  
      <br />  
      <img src="./kaptcha/getKaptchaImage.do" id="kaptchaImage"  style="margin-bottom: -3px"/>  
      <a href="#" onclick="changeCode()">看不清?换一张</a>  
</div>  
</body>  
</html>  
</span>  