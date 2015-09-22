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
        background: #b0d0ef;
        position: relative;
        height: 5px;
    }

    .ui-progressbar-value {
        background: #3d8bf2;
    }
</style>


<script type="text/javascript">
$(function () {

    $.ajaxSetup({cache: false });
    var pro = $("#progressbar");   //进度条div
    pro.progressbar({
        value: false   //初始化的值为0

    });
    setTimeout(addValue, 5000);

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
        $("#err1").text("长度过长");
        $("#name").addClass("text_error");
        return false;
    }
    else {

        if (emailValue.replace(/\s/g, "") == "") {
            $("#err2").text("请输入邮箱地址");
            $("#email").addClass("text_error");
            return false;
        }
        else {
            if (emailValue.length > 99) {
                $("#err2").text("长度过长");
                $("#email").addClass("text_error");
                return false;
            }
            var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
            isok = reg.test(emailValue);
            if (!isok) {
                $("#err2").text("邮箱地址格式有误");
                $("#email").addClass("text_error");
                return false;
            }
            else {

                if (subjectValue.length > 40) {
                    $("#err3").text("长度过长");
                    $("#subject").addClass("text_error");
                    return false;
                }

                else {

                    if (messageValue.replace(/\s/g, "") == "") {
                        $("#err4").text("请输入内容");
                        $("#message").addClass("text_error");
                        return false;
                    }
                    if (messageValue.length > 50) {
                        $("#err4").text("长度过长");
                        $("#message").addClass("text_error");
                        return false;
                    }
                    if (verifyCodeValue.replace(/\s/g, "") == "") {
                        $("#err5").text("请输入验证码");
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
                            $("#err5").text("验证码错误");
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
        var num = $("#email").val().substr(0, 50);
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
    <h2>关于</h2>

    <p>Picoo桌面是体积最小，速度最快，最省电的安卓用户系统。全面采用Google
        Material设计风格，用户无需升级即可感受全新安卓5.0的全新流畅体验。不仅如此，Picoo桌面还提供了智能文件夹、以及快捷设置界面，致力于提供最好，最优质的安卓使用体验。更多功能正在紧锣密鼓的制作中，感谢您的支持！</p>

    <p>联系方式：<strong>picoolauncher@gmail.com</strong></p>

    <div class="text_w">
        <p>
            <input type="text" class="text" placeholder="您的姓名(不超过10字)" name="name" id="name"
                   oninput="words_deal_name();"/>
        </p>

        <p id="err1" class="error_tips">不能为空</p>
    </div>
    <div class="text_w">
        <p>
            <input type="text" class="text" placeholder="您的邮箱地址" name="email" id="email" oninput="words_deal_email();"/>
            <em>(必填)</em>
        </p>

        <p id="err2" class="error_tips">不能为空</p>
    </div>
    <div class="text_w">
        <p>
            <input type="text" class="text" placeholder="标题(不超过10字)" id="subject" name="subject"
                   oninput="words_deal_subject();"/>
        </p>

        <p id="err3" class="error_tips">不能为空</p>
    </div>
    <div class="text_w">
        <input class="text" placeholder="内容(不超过200字)" id="message" name="message" oninput="words_deal_message();"/>
        <em>(必填)</em>

        <p id="err4" class="error_tips">不能为空</p>
    </div>
    <div class="text_w">
        <p>
            <input name="kaptcha" type="text" id="kaptcha" class="text_k" placeholder="验证码">
            <img src="./kaptcha/getKaptchaImage.do" id="kaptchaImage" style="top:10px;position: relative" width="154"
                 height="35"></p>

        <p id="err5" class="error_tips">不能为空</p>
    </div>
    <div class="text_w">
        <p>
            <input type="submit" id="send" class="send " value="发送"></p>

    </div>
</form>
</body>
</html>