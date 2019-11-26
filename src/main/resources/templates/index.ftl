<html lang="en">
<head>
    <title>SpringBoot + Freemarker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <script src="${request.contextPath}/static/js/jquery-3.4.1.min.js"></script>
</head>
<style>
    .panel_table{
        width: 800px;
        margin: 0 auto;
        border: 1px solid black;
        text-align: center;
    }
    td{
        text-align: center;
    }
    .addnote{
        float: right;
        margin-right: 10px;
    }
</style>
<body>
<p></p>
<div class="panel_table">
    <div style="width: 800px;height: 25px;"><input type="button" class="addnote" value="新增"></div>
    <table class="table_1">
        <tr>
            <td style="width: 20%">
                标题
            </td>
            <td style="width: 46%">
                内容
            </td>
            <td style="width: 25%">
                创建时间
            </td>
            <td>
                操作
            </td>
        </tr>

        <#list notelist as note>
            <tr id="${note.id}">
                <td>${note.title}</td>
                <td>${note.content}</td>
                <td>
                    ${long2date(note.createtime,"yyyy-MM-dd HH:mm:ss")}
                    <#--${note.createtime?number?number_to_datetime}-->
                </td>
                <td>
                    <a class="update"  href="${request.contextPath}/demo/insert.html?id=${note.id}">修改</a>
                    <a class="delete"  href="javascripit:;">删除</a>
                </td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>
<script>

    $(".addnote").click(function(){
        debugger
        location.href="${request.contextPath}/demo/insert.html";
    });

    $(".delete").click(function () {
        if(confirm("确定删除吗？")){
            var id=$(this).parent().parent().attr("id");
            $.post("${request.contextPath}/demo/delete.html",{
                id:id
            },function (response) {
                var code = response.code;
                if(code==1){
                    location.reload();
                }else{
                    alert("删除失败！");
                }
            })
        }else{

        }

    });
</script>