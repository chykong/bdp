<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../../common/taglib.jsp" %>
<head>
    <title>功能管理</title>
</head>

<body>
<div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
    <ul class="breadcrumb">
        <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
        <li class="active">系统管理</li>
        <li class="active">功能管理</li>
    </ul>
</div>

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

                <div class="widget-body">
                    <div class="widget-main">
                        <table class="searchField" style="margin: 4px; padding: 4px;">
                            <tr>
                                <td>
                                    <button class="btn btn-primary btn-sm" id="btnSearch">
                                        <i class="ace-icon fa fa-search"></i>刷新
                                    </button>
                                    <c:if test="${critc:isP('SysResourceAdd')}">
                                        <button type="button" class="btn btn-success btn-sm" id="btnAdd">
                                            <i class="ace-icon fa fa-plus bigger-110"></i>新增
                                        </button>
                                    </c:if>

                                    <button type="button" class="btn  btn-sm"
                                            onclick="location.href='${dynamicServer}/sys/resource/index.htm'">
                                        <i class="ace-icon fa fa-undo bigger-110"></i>返回
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
                    <th width=120>资源名称</th>
                    <th width="120">资源代码</th>
                    <th>资源链接</th>
                    <th style="text-align: center;">排序</th>
                    <th width=160>操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${list}" var="resource" varStatus="st">
                    <tr>
                        <td>${st.index+1 }</td>
                        <td>${resource.name}</td>
                        <td>${resource.code}</td>
                        <td>${resource.url}</td>
                        <td>${resource.displayOrder}</td>
                        <td><c:if test="${critc:isP('SysResourceUpdate')}">
                            <a href="toUpdate.htm?id=${resource.id}&backUrl=${backUrl}"> 修改</i>
                            </a>
                        </c:if> <c:if test="${critc:isP('SysResourceDelete')}">
                            <a href="javascript:delFunction(${resource.id });"> 删除 </a>
                        </c:if></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>
<critc-script>

    <script type="text/javascript">
        $(function () {
            $("#btnSearch").bind('click', searchFunction);
            $("#btnAdd").bind('click', addFunction);
        })

        // 查询方法
        var searchFunction = function () {
            var url = "functionIndex.htm?parentId=${resource.id}";
            window.location = encodeURI(url);
        }
        // 删除
        var delFunction = function (id) {
            bootbox.confirm("你确定要删除该功能吗？", function (result) {
                if (result) {
                    window.location = "delete.htm?id=" + id + "&backUrl=${backUrl}";
                }
            })
        }
        //新增
        var addFunction = function (id) {
            window.location = 'toAdd.htm?parentId=${resource.id}&backUrl=${backUrl }';
        }
    </script>
</critc-script>