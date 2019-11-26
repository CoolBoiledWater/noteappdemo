<html lang="en">
<head>
    <title>SpringBoot + Freemarker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="/static/js/jquery-3.4.1.min.js"></script>
</head>
<style>
    .panel_table{
        width: 260px;
        margin: 0 auto;
    }
    input{
        margin: 5px;
    }
</style>
<body>
<div class="panel_table">
        用户名：<input type="text" id="name" autocomplete="off" placeholder="请输入用户"><br>
        密&nbsp;&nbsp;&nbsp;码：<input type="password" id="password" autocomplete="off" placeholder="请输入密码"><br>
        <input type="button" class="submit" value="确定">
</div>
</body>
</html>
<script>
    $(".submit").click(function () {
            var name = $("#name").val();
            var password = $("#password").val();
            if (name.length<=0 || password.length<=0){
                alert("用户民或密码不能为空！");
                return false;
            }
            $.post("${request.contextPath}/user/dologin",{
                name:name,
                password:password
            },function (t) {
                if (t.code==1){
                    location.href="${request.contextPath}/demo/index.html";
                } else{
                    alert("用户名或密码错误！");
                }
            })
    })
</script>