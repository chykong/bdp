<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../../common/taglib.jsp" %>
<head>
    <title>操作日志</title>
</head>
<body>

<div class="main-content">
    <div class="main-content-inner">
        <!-- #section:basics/content.breadcrumbs -->
        <div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
            <ul class="breadcrumb">
                <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
                <li class="active">系统管理</li>
                <li class="active">操作日志</li>
            </ul>
            <!-- /.breadcrumb -->
        </div>

        <!-- /section:basics/content.breadcrumbs -->
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box widget-color-blue">
                        <!-- #section:custom/widget-box.options -->
                        <div class="widget-header">
                            <h5 class="widget-title bigger lighter">
                                <i class="ace-icon fa fa-table"></i> 操作面板
                            </h5>
                        </div>

                        <!-- /section:custom/widget-box.options -->
                        <div class="widget-body">
                            <div class="widget-main">
                                <table class="searchField" style="margin: 4px; padding: 4px;">
                                    <tr>
                                        <td>用户：</td>
                                        <td><form:select path="sysLogSearchVO.userId" class="form-control input-small"
                                                         id="userId">
                                            <form:option value="" label="-选择-"/>
                                            <form:options items="${listUser}" itemValue="id" itemLabel="realname"/>
                                        </form:select></td>
                                        <td>起止日期：</td>
                                        <td><input type="text" id="startDate" class="form-control input-small"
                                                   placeholder="" value="${sysLogSearchVO.startDate }">
                                        </td>
                                        <td>至</td>
                                        <td><input type="text" id="endDate" class="form-control input-small"
                                                   placeholder="" value="${sysLogSearchVO.endDate }"></td>
                                        <td>
                                            <button class="btn btn-primary btn-sm" id="btnSearch">
                                                <i class="ace-icon fa fa-search"></i> 查询
                                            </button>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- PAGE CONTENT BEGINS -->
            <div class="row">
                <div class="col-xs-12">
                    <table id="simple-table" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th width=40>#</th>
                            <th width="100">用户姓名</th>
                            <th>操作时间</th>
                            <th width="100">模块名称</th>
                            <th>操作名称</th>
                            <th>操作url</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${list }" var="sysLog" varStatus="st">
                            <tr>
                                <td>${st.index+1 }</td>
                                <td>${sysLog.realname }</td>
                                <td><fmt:formatDate value="${sysLog.operaDate}" pattern="yyyy-MM-dd HH:mm"/></td>
                                <td>${sysLog.moduleName }</td>
                                <td>${sysLog.operaName }</td>
                                <td>${sysLog.operaUrl }</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /.span -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-xs-12">${ pageNavigate.pageModel}</div>
            </div>

        </div>
        <!-- /.main-content -->
    </div>
</div>
<!-- /.main-container -->

<script type="text/javascript">
    $(function () {
        $("#btnSearch").bind('click', searchUser);

        $('#startDate').datetimepicker({
            lang: 'ch',
            timepicker: false,
            format: 'Y-m-d'
        });
        $('#endDate').datetimepicker({
            lang: 'ch',
            timepicker: false,
            format: 'Y-m-d'
        });
    })

    // 查询方法
    var searchUser = function () {
        var url = "index.htm?";
        if ($("#userId").val() != '')
            url += "userId=" + $("#userId").val();
        if ($("#startDate").val() != '')
            url += "&startDate=" + $("#startDate").val();
        if ($("#endDate").val() != '')
            url += "&endDate=" + $("#endDate").val();
        window.location = encodeURI(url);
    }

</script>
</body>
