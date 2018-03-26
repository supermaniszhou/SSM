<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/17 0017
  Time: 下午 4:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
%>
<html>
<head>
    <title>Title</title>
    <script src="<%=path%>/bootstrap3.0/bootstrapDialog/dist/jquery2.2.js" crossorigin="anonymous"></script>
    <link type="text/css" rel="stylesheet" href="<%=path%>/bootstrap3.0/bootstrap/css/bootstrap.min.css" crossorigin="anonymous"/>
    <link type="text/css" rel="stylesheet" href="<%=path%>/bootstrap3.0/bootstrap/css/bootstrap-theme.min.css" crossorigin="anonymous"/>
    <link type="text/css" rel="stylesheet" href="<%=path%>/bootstrap3.0/bootstrapTable/bootstrap-table.min.css" crossorigin="anonymous"/>
    <script src="<%=path%>/bootstrap3.0/bootstrap/js/bootstrap.min.js" crossorigin="anonymous"></script>
    <script src="<%=path%>/bootstrap3.0/bootstrapTable/bootstrap-table.js" crossorigin="anonymous"></script>
    <script src="<%=path%>/bootstrap3.0/bootstrapTable/locale/bootstrap-table-zh-CN.js" crossorigin="anonymous"></script>
</head>
<body>
<table id="table2"></table>

<table id ="table3">
    <thead>
        <tr>
            <th>
                <input field="key" value="新文件名">
            </th>
        </tr>
    </thead>
</table>
<script>
    $(function () {
        initTable();
    });
    //初始化数据显示
    function initTable(){
        $.post("/filelist/list", null, function (data) {
            var j = JSON.parse(data);
            for (var i=0;i<j.length;i++){
                j[i].key='<%=basePath%>'+j[i].key;
            }
            $('#table2').bootstrapTable({
                data: j,
                dataType: "json",
                pagination: false,
                striped: true,                   //是否显示行间隔色
                cache: false,
                uniqueId: "key",                       //每一行的唯一标识，一般为主键列
//                height: 500,
                search: true,
                showRefresh: true,
                detailView: true,
                sortable: true,
                columns: [
                    {checkbox: true}
                    , {
                        field: 'key',
                        title: '唯一名称'
                    }, {
                        field: 'value',
                        title: '源文件名称'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        formatter: opeate
                    }
                ]
            });
        });
    }

    function opeate(value, row, index) {
        console.log(row);
        var html = "<a href='${pageContext.request.contextPath}/downloadServlet?filename="+row.key+"'>下载</a>";
        return html;
    }
    //
    //    //查询条件
    //    function queryParams(params) {
    //        return {};
    //    }
    //
    //    //查询事件
    //    function SearchData() {
    //        $('#table').bootstrapTable('refresh', {pageNumber: 1});
    //    }
    //
    //    //删除操作
    //    function DeleteBook(bookId) {
    //        if (confirm("确定删除图书ID：" + bookId + "吗？")) {
    //            alert("执行删除操作");
    //        }
    //    }
</script>


</body>
</html>
