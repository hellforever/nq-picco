<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 15-7-2
  Time: 下午4:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发送邮件</title>
<link rel="stylesheet" href="http://192.8.19.77/css/email.css">
<script type="text/javascript"
        src="http://rwww.picooapp.com/js/jquery-1.11.3.min.js?ver=${timestamp}"></script>
<script src="http://rwww.picooapp.com/js/jquery-ui.js?ver=${timestamp}"></script>
<link rel="stylesheet" href="http://rwww.picooapp.com/css/jquery-ui.css?ver=${timestamp}"/>
<style>

    .ui-progressbar {
        border: none;
        background : #b0d0ef;
        position: relative;
        height: 5px;
    }

    .ui-progressbar-value{
        background : #3d8bf2;
    }
</style>


<script type="text/javascript">
$(function () {

    $.ajaxSetup({cache: false });
    var pro = $("#progressbar");   //进度条div

    pro.progressbar({
        value: false   //初始化的值为0

    });
    setTimeout(addValue, 500);

    //动态修改value的函数
    function addValue() {
        var pro = $("#progressbar");
        var newValue;
        if (pro.progressbar("value") > 0) {
            newValue = pro.progressbar("value") + 1;
        }


        pro.progressbar("value", newValue); //设置新值
        if (newValue >= 95) {
            return;
        }    //超过100时，返回

        setTimeout(addValue, 20); //延迟20毫秒递归调用自己
    }

    pro.hide();
    $(".error_tips").text("");
    $("#kaptchaImage")
            .click(
            function () {//生成验证码
                $(this).hide().attr(
                                'src',
                                './kaptcha/getKaptchaImage.do?'
                                        + Math.floor(Math.random() * 100))
                        .fadeIn();
                event.cancelBubble = true;
            });
});

//延迟500毫秒调用修改value的函数


function validateForm() {
    $(".text_error").removeClass("text_error");
    $(".error_tips").text("");
    var verifyCodeValue = $("#kaptcha").val();
    var nameValue = $("#name").val();
    var subjectValue = $("#subject").val();
    var emailValue = $("#email").val();
    var messageValue = $("#message").val();
    if (nameValue.length > 40) {
        $("#err1").text("too long");
        $("#name").addClass("text_error");
        return false;
    }
    else {

        if (emailValue.replace(/\s/g, "") == "") {
            $("#err2").text("Your Email can not be empty");
            $("#email").addClass("text_error");
            return false;
        }
        else {
            if (emailValue.length > 50) {
                $("#err2").text("too long");
                $("#email").addClass("text_error");
                return false;
            }
            var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
            isok = reg.test(emailValue);
            if (!isok) {
                $("#err2").text("Invalid email address");
                $("#email").addClass("text_error");
                return false;
            }
            else {

                if (subjectValue.length > 40) {
                    $("#err3").text("too long");
                    $("#subject").addClass("text_error");
                    return false;
                }

                else {

                    if (messageValue.replace(/\s/g, "") == "") {
                        $("#err4").text("Message can not be empty");
                        $("#message").addClass("text_error");
                        return false;
                    }
                    if (messageValue.length > 200) {
                        $("#err4").text("message is too long");
                        $("#message").addClass("text_error");
                        return false;
                    }
                    if (verifyCodeValue.replace(/\s/g, "") == "") {
                        $("#err5").text("CAPTCHA can not be empty");
                        $("#kaptcha").addClass("text_error");
                        return false;
                    }

                    else {
                        //提交前先异步检查验证码是否输入正确

                        var jsessionid = "<%=session.getId()%>";

                        var verifyUrl = "./check.do?verifyCode="
                                + verifyCodeValue + "&sessionid=" + jsessionid;
//                                alert(verifyUrl);
                        var htmlobj = $.ajax({url: verifyUrl,
                            async: false

                        });
                        var jsessionid2 = "<%=session.getId()%>";
//                                alert(jsessionid2);
                        var xmltext = htmlobj.responseText;

//                                alert(xmltext);
                        if (xmltext == 'Y') {

                            var pro = $("#progressbar");
                            pro.progressbar("value", 1);

                            $("#progressbar").show();

                            return true;
                        }

                        else {
                            $("#err5").text("CAPTCHA error");
                            $("#kaptcha").addClass("text_error");
                            return false;

                        }
                    }
                }

            }


        }
    }

}


function changeCode() {
    $('#kaptchaImage').hide();
    $('#kaptchaImage').attr(
            'src',
            './kaptcha/getKaptchaImage.do?'
                    + Math.floor(Math.random() * 100));
    $('#kaptchaImage').fadeIn();
    event.cancelBubble = true;
}
function words_deal_name() {
    var curLength = $("#name").val().length;
    if (curLength > 5) {
        var num = $("#name").val().substr(0, 20);
        $("#name").val(num);

    }

}
function words_deal_subject() {
    var curLength = $("#subject").val().length;
    if (curLength > 5) {
        var num = $("#subject").val().substr(0, 20);
        $("#subject").val(num);

    }

}
function words_deal_email() {
    if ($("#email").val().length > 0 && $("#message").val().length > 0) {
        $("#send").addClass("send_ok");
    }
    else {
        $("#send").removeClass("send_ok");
    }
    var curLength = $("#email").val().length;
    if (curLength > 50) {
        var num = $("#email").val().substr(0,50);
        $("#email").val(num);

    }

}
function words_deal_message() {

    if ($("#email").val().length > 0 && $("#message").val().length > 0) {
        $("#send").addClass("send_ok");
    }
    else {
        $("#send").removeClass("send_ok");
    }
    var curLength = $("#message").val().length;
    if (curLength > 200) {
        var num = $("#message").val().substr(0, 200);
        $("#message").val(num);

    }

}
</script>

</head>

<body>
<form method="post" action="./addContact.do" onsubmit="return validateForm()">
    <div id="progressbar" class="ui-progressbar"></div>
    <h2>About</h2>
    <p>Picoo Launcher is the fastest, smallest, and most power-saving phone launcher created on the market with Google Material Design. It is 100% faster than any default system launcher, and guarantees the best experience even on low configuration phones. More new features will be provided, and thank you for your support.</p>
    <p>Drop us a line through <strong>picoolauncher@gmail.com</strong></p>
    <div class="text_w">
        <p>
            <input type="text" class="text" placeholder="Your Name" name="name" id="name" oninput="words_deal_name();"/>
        </p>

        <p id="err1" class="error_tips"></p>
    </div>
    <div class="text_w">
        <p>
            <input type="text" class="text" placeholder="Your Email" name="email" id="email"
                   oninput="words_deal_email();"/>
            <em>(necessary)</em>
        </p>

        <p id="err2" class="error_tips">Your Email can not be empty</p>
    </div>
    <div class="text_w">
        <p>
            <input type="text" class="text" placeholder="Subject" id="subject" name="subject"
                   oninput="words_deal_subject();"/>
        </p>

        <p id="err3" class="error_tips"></p>
    </div>
    <div class="text_w">
        <input class="text" placeholder="Message" id="message" name="message"
               oninput="words_deal_message();"/>
        <em>(necessary)</em>
        <p id="err4" class="error_tips">Message can not be empty</p>
    </div>
    <div class="text_w">
        <p>
            <input name="kaptcha" type="text" id="kaptcha" class="text_k" placeholder="CAPTCHA">
            <img src="./kaptcha/getKaptchaImage.do" id="kaptchaImage" style="top:10px;position: relative"
                 width="154" height="35"></p>

        <p id="err5" class="error_tips">CAPTCHA can not be empty</p>
    </div>
    <div class="text_w">
        <p>
            <input type="submit" id="send" class="send" value="Send"></p>

    </div>
</form>
</body>
</html>