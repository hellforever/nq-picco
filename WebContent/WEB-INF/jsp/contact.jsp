<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>jQuery UI Progressbar - Custom Label</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <style>

  .ui-progressbar {
            position: relative;
        }
        .progress-label {
            position: absolute;
            left: 50%;
            top: 4px;
            font-weight: bold;
            text-shadow: 1px 1px 0 #fff;
        }
    </style>
    <script>
        $(function() {
            var pro = $( "#progressbar" );   //进度条div
            var proLabel = $( ".progress-label" ); //进度条里面文字
            pro.progressbar({
                value: false,   //初始化的值为0
                change: function() {
                    //当value值改变时，同时修改label的字
                    proLabel.text( pro.progressbar( "value" ) + "%" );
                },
                complete: function() {
                    //当进度条完成时，显示complate
                    proLabel.text( "Complete!" );
                }
            });

            //延迟500毫秒调用修改value的函数
            setTimeout( addValue, 500);

            //动态修改value的函数
            function addValue(){
                var pro = $( "#progressbar" );
                var newValue = pro.progressbar("value") +1;

                pro.progressbar("value",newValue); //设置新值
                if( newValue >= 100) {return;}    //超过100时，返回

                setTimeout( addValue, 20); //延迟500毫秒递归调用自己
            }
        });
    </script>
</head>
<body>
<div id="progressbar"><div class="progress-label">Loading...</div></div>

</body>
</html>