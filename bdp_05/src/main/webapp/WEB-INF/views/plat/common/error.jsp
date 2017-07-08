<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<head>
    <title>${webTitle }-操作失败</title>
</head>

<body>
<div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
    <ul class="breadcrumb">
        <li><i class="ace-icon fa fa-home home-icon"></i> <a href="${dynamicServer}/index.htm">首页</a></li>
        <li class="active">操作结果</li>
    </ul>
</div>

<div class="page-content">
    <div class="alert alert-danger" style="text-align: center;">
        <h4>
            <i class="fa fa-exclamation-circle"></i> ${msg }
        </h4>
        <a href="javascript:void(0)" onclick='history.back(-1);'>返回</a>
    </div>
</div>
</body>
</html>
