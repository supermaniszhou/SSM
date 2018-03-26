<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/17 0017
  Time: 下午 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI() + path;
%>
<html>
<head>
    <title>文件上传</title>
    <script src="<%=path%>/bootstrap3.0/bootstrapDialog/dist/jquery2.2.js" crossorigin="anonymous"></script>

    <link type="text/css" rel="stylesheet" href="<%=path%>/bootstrap3.0/bootstrap/css/bootstrap.min.css" crossorigin="anonymous"/>
    <link type="text/css" rel="stylesheet" href="<%=path%>/bootstrap3.0/bootstrap/css/bootstrap-theme.min.css" crossorigin="anonymous"/>
    <link type="text/css" rel="stylesheet" href="<%=path%>/bootstrap3.0/bootstrapTable/bootstrap-table.min.css" crossorigin="anonymous"/>
    <link type="text/css" rel="stylesheet" href="<%=path%>/bootstrap3.0/bootstrap-fileinput/css/fileinput.min.css" crossorigin="anonymous"/>

    <script src="<%=path%>/bootstrap3.0/bootstrap/js/bootstrap.min.js" crossorigin="anonymous"></script>
    <script src="<%=path%>/bootstrap3.0/bootstrapTable/bootstrap-table.js" crossorigin="anonymous"></script>
    <script src="<%=path%>/bootstrap3.0/bootstrapTable/locale/bootstrap-table-zh-CN.js" crossorigin="anonymous"></script>
    <script src="<%=path%>/bootstrap3.0/bootstrap-fileinput/js/fileinput.min.js" crossorigin="anonymous"></script>
    <script src="<%=path%>/bootstrap3.0/bootstrap-fileinput/js/locales/zh.js" crossorigin="anonymous"></script>


</head>
<body>
<h2>文件上传</h2>
<form action="${pageContext.request.contextPath}/uploadServlet" enctype="multipart/form-data" method="post">
    <table>
        <tr>
            <td>文件描述:</td>
            <td><input type="text" name="description"></td>
        </tr>
        <tr>
            <td>请选择文件:</td>
            <td><input type="file" name="file1"></td>
            <td><input type="file" name="file2"></td>
        </tr>

        <tr>
            <td><input type="submit" value="上传"></td>
        </tr>
    </table>
</form>


<br>
<hr>
<h1>这是上传</h1>
<div class="form-group">
    <div class="file-loading">
        <label>Preview File Icon</label>
        <input id="file-3" type="file" title="上传" multiple>
    </div>
    <script>
        $("#file-3").fileinput({
            theme: 'fa',
            showUpload: true,
            showCaption: false,
            showCancel: true,
            uploadLabel: "开始上传",
            uploadClass: "btn btn-primary",//设置上传按钮样式
            language: "zh",//配置语言
//            uploadUrl: "/uploadServlet",
            error: 'zheshiyigecuosh',
//            browseClass: "btn btn-primary btn-lg",
//            fileType: "any",
//            language: "zh",//配置语言
//            previewFileIcon: "<i class='glyphicon glyphicon-king'>ttt</i>",
            overwriteInitial: false,
            initialPreviewAsData: true

        });
        //当文本框中的内容发生改变时，就会触发这个事件。
        $('#file-3').on('change', function (event) {
            console.log("变化");
            alert("变化");
        });
    </script>
</div>
<hr>
<label class="col-sm-2 control-label">图片上传</label>
<%--//后台的接受名为file,要相同名字，id为初始化文件框--%>
<div style="height: 300px;width: 600px;">

    <form id="form1" action="${pageContext.request.contextPath}/upload/toupload" enctype="multipart/form-data" method="post">
        图片id<input type="text" name="id" id="picid"><br>
        图片名称:<input type="text" name="picName"><br>
        <input id="file-pic" name="file" type="file" multiple>
        <input type="submit" value="保存"><br>
    </form>
</div>
<script>
    $(function () {

    });
    $('#file-pic').fileinput({//初始化上传文件框
        theme: 'fa',
        showUpload: true,
        showRemove: true,
        showBrowse: true,
        uploadAsync: true,
        uploadLabel: "开始上传",//设置上传按钮的汉字
        uploadClass: "btn btn-primary",//设置上传按钮样式
        showCaption: false,//是否显示标题
        language: "zh",//配置语言
        uploadUrl: "/upload/toupload",
        //dropZoneEnabled: false,//是否显示拖拽区域
        minImageWidth: 50, //图片的最小宽度
        minImageHeight: 50,//图片的最小高度
//        maxImageWidth: 100,//图片的最大宽度
//        maxImageHeight: 100,//图片的最大高度
        maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
//        maxFileSize: 0,
        maxFileCount: 3, /*允许最大上传数，可以多个，当前设置单个*/
        enctype: 'multipart/form-data',
        //allowedPreviewTypes : [ 'image' ], //allowedFileTypes: ['image', 'video', 'flash'],
        allowedFileExtensions: ["jpg", "png", "gif"], /*上传文件格式*/
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        dropZoneTitle: "请通过拖拽图片文件放到这里",
        dropZoneClickTitle: "或者点击此区域添加图片",
        //uploadExtraData: {"id": id},//这个是外带数据
        showBrowse: false,
        browseOnZoneClick: true,
        layoutTemplates:{
//             actionDelete:'', //去除上传预览的缩略图中的删除图标
            actionUpload:'',//去除上传预览缩略图中的上传图片；
//            actionZoom:'',   //去除上传预览缩略图中的查看详情预览的缩略图标。
            progress:''
        },
        slugCallback: function (filename) {
            return filename.replace('(', '_').replace(']', '_');
        }

    });
    //异步上传返回结果处理
    $('#file-pic').on("fileuploaded", function (event, data, previewId, index) {
        alert(10);
        var result = data.response; //后台返回的json
        //console.log(result.status);
        //console.log(result.id);
        $('#picid').val(result.id);//拿到后台传回来的id，给图片的id赋值序列化表单用
        //如果是上传多张图
        //计数标记，用于确保全部图片都上传成功了，再提交表单信息
        var fileCount = $('#file-pic').fileinput('getFilesCount');
//        if (fileCount == 3) {
//            $.ajax({//上传文件成功后再保存图片信息
//                url: 'BannerPicAction!savaForm.action',
//                data: $('#form1').serialize(),//form表单的值
//                success: function (data, status) {
//
//                },
//                cache: false,                    //不缓存
//            });
//        }
        $.ajax({//上传文件成功后再保存图片信息
            url: 'BannerPicAction!savaForm.action',
            type: 'post',
            dataType: 'json',
            data: $('#form1').serialize(),//form表单的值
            success: function (data, status) {
                if (status == "success") {

                    if (data.status == "success") {//提交成功

                        //跳转回添加页面

                    } else {
                        alert("添加失败,编码的错误!");
                    }

                } else {
                    alert("添加失败,ajax请求返回失败!");
                }
            },
            cache: false,                    //不缓存
        });
    });
    $('#savePic').on('click', function () {// 提交图片信息 //
        //先上传文件，然后在回调函数提交表单
        $('#file-pic').fileinput('upload');

    });
    $('.myfile').on('fileerror', function (event, data, msg) {
        console.log("fileerror");
        console.log(data);
    });
    //上传文件成功，回调函数

</script>


<%--<form enctype="multipart/form-data">--%>
    <%--<div class="file-loading">--%>
        <%--<input id="kv-explorer" type="file" multiple>--%>
    <%--</div>--%>
<%--</form>--%>
<script>
    $(function () {
        $("#kv-explorer").fileinput({
            'theme': 'explorer-fa',
            'uploadUrl': '/uploadServlet',
            overwriteInitial: false,
            initialPreviewAsData: true,
//            initialPreview: [
//                "http://lorempixel.com/1920/1080/nature/1",
//                "http://lorempixel.com/1920/1080/nature/2",
//                "http://lorempixel.com/1920/1080/nature/3"
//            ],
//            initialPreviewConfig: [
//                {caption: "nature-1.jpg", size: 329892, width: "120px", url: "{$url}", key: 1},
//                {caption: "nature-2.jpg", size: 872378, width: "120px", url: "{$url}", key: 2},
//                {caption: "nature-3.jpg", size: 632762, width: "120px", url: "{$url}", key: 3}
//            ]
        });
    });
</script>

</body>
</html>
