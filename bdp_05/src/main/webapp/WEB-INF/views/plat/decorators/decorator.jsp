<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="${webTitle}"/>
    <title><sitemesh:write property='title'/> - ${webTitle}</title>

    <%@include file="../common/styles.jspf" %>
    <sitemesh:write property='head'/>
    <sitemesh:write property='critc-css'/>
</head>
<body class="no-skin">
<%@ include file="../common/header.jspf" %>
<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jspf" %>
    <div class="main-content">
        <div class="main-content-inner">
            <sitemesh:write property='body'/>
        </div>
    </div>
</div>
<%@include file="../common/scripts.jspf" %>
<sitemesh:write property='critc-script'/>
</body>
</html>
