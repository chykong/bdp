<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../../common/taglib.jsp" %>

<c:if test="${empty list}">
    <div class="row">
        <div class="col-xs-12">暂无数据</div>
    </div>
</c:if>
<c:if test="${!empty list && list.size() > 0}">
<div class="row">
    <div class="col-xs-12">
        <table id="simple-table" class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <th width=40>#</th>
                <th width=140>登录时间</th>
                <th width="120">登录IP</th>
                <th>终端</th>
                <th>浏览器类型</th>
                <th>浏览器版本</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list }" var="sysUserLogin" varStatus="st">
                <tr>
                    <td>${st.index+1 }</td>
                    <td><fmt:formatDate value="${sysUserLogin.loginDate }" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td>${sysUserLogin.loginIp }</td>
                    <td>${sysUserLogin.terminal }</td>
                    <td>${sysUserLogin.explorerType }</td>
                    <td>${sysUserLogin.explorerVersion}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="row">
    <div class="col-xs-12" id="pageNavForward">${ pageNavigate.pageModel}</div>
</div>
</c:if>
<script type="text/javascript">
	$(function() {
		$("#pageNavForward").on('click', 'a', function(e) {
			var $link = $(this);
			var $container = $link.closest('.ui-dialog-content');
			$container.load( $link.attr('href'));
			return false;
		});
		$("#forward_div").css("max-height", $(window).height() - 300);
	})
</script>

