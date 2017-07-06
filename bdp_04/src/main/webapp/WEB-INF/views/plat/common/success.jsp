<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/taglib.jsp" %>
<head>
    <title>${webTitle }-操作成功</title>
</head>
<body>
<div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
    <ul class="breadcrumb">
        <li><i class="ace-icon fa fa-home home-icon"></i> <a href="${dynamicServer}/index.htm">首页</a></li>
        <li class="active">操作结果</li>
    </ul>
</div>

<div class="page-content">
    <div class="alert alert-success" style="text-align: center;">
        <h4>
            <i class="fa fa-check-circle"></i> ${msg }
        </h4>
        <a href="${backUrl}">如果你的浏览器没有自动跳转，请点击此链接</a>
        <script type="text/javascript">
							setTimeout(function() {
								location.href = "${backUrl}";
							}, 2000);

        </script>
    </div>
</div>
</body>
</html>
