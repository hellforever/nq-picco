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
<link rel="stylesheet" href="css/email.css?ver=${timestamp}">
<script type="text/javascript"
        src="http://rwww.picooapp.com/js/jquery-1.11.3.min.js?ver=${timestamp}"></script>
<script src="http://rwww.picooapp.com/js/jquery-ui.js?ver=${timestamp}"></script>
<link rel="stylesheet" href="http://rwww.picooapp.com/css/jquery-ui.css?ver=${timestamp}" />
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
    var pro = $( "#progressbar" );   //进度条div
    pro.progressbar({
        value: false   //初始化的值为0

    });
    setTimeout( addValue, 500);

    //动态修改value的函数
    function addValue(){
        var pro = $( "#progressbar" );
        var newValue;
        if(pro.progressbar("value")>=0){ newValue = pro.progressbar("value") +1;}


        pro.progressbar("value",newValue); //设置新值
        if( newValue >= 95) {return;}    //超过100时，返回

        setTimeout( addValue, 200); //延迟20毫秒递归调用自己
    }


});

//延迟500毫秒调用修改value的函数








</script>

</head>

<body>


    <div id="progressbar" class="ui-progressbar"></div>

</body>
</html>