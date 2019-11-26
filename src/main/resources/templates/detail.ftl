<html lang="en">
<head>
    <title>SpringBoot + Freemarker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="${request.contextPath}/static/js/jquery-3.4.1.min.js"></script>
</head>
<style>
    .panel_table{
        width: 220px;
        margin: 0 auto;
    }
    input{
        margin: 5px;
    }
</style>
<body>
<div class="panel_table">
    <form action="/demo/doinsert">
        标题：<input type="text" id="title" autocomplete="off" placeholder="请输入标题" value="${note.title}"><br>
        内容：<input type="text" id="content" autocomplete="off" placeholder="请输入内容" value="${note.content}"><br>
        <input type="hidden" id="createtime" value="${note.createtime}">
        <input type="hidden" id="updateime" value="${note.updatetime}">
        <input type="hidden" id="status" value="${note.status}">
        <input type="hidden" id="noteid" value="${note.id}">
        <input type="button" class="submit" value="确定">
        <input type="button" class="back"  value="取消" onclick="history.back()">
    </form>
</div>
</body>
</html>
<script>

    $(".submit").click(function () {
        if (confirm("确定提交？")){
            var title = $("#title").val();
            var content = $("#content").val();
            var id = $("#noteid").val();
            var createtime = $("#createtime").val();
            var status = $("#status").val();
            if (title.length<=0 || content.length<=0){
                alert("标题或内容不能为空！");
                return false;
            }
            if (id!=""){
                $.post("${request.contextPath}/demo/doupdate",{
                    id:id,
                    title:title,
                    content:content
                },function (t) {
                    if (t.code==1){
                        alert("修改成功！");
                        location.href="${request.contextPath}/demo/index.html";
                    }else{
                        alert("修改失败！")
                    }
                })
            }else{
                $.post("${request.contextPath}/demo/doinsert",{
                    title:title,
                    content:content
                },function (t) {
                    if (t.code==1){
                        alert("添加成功！");
                        location.href="${request.contextPath}/demo/index.html";
                    }else{
                        alert("添加失败！")
                    }
                })
            }

        }
    })
</script>