<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../../common/taglib.jsp" %>
<head>
    <title>资源管理</title>
    <critc-css>
    <link href="${staticServer}/assets/treetable/treeTable.min.css?version=${versionNo}" rel="stylesheet" type="text/css"/>
    </critc-css>
</head>

<body>

<div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
    <ul class="breadcrumb">
        <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
        <li class="active">系统管理</li>
        <li class="active">资源管理</li>
    </ul>
</div>

<div class="page-content">
    <div class="row">
        <div class="col-xs-12">
            <div class="widget-box widget-color-blue">
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
                                        <i class="ace-icon fa fa-search"></i> 刷新
                                    </button>
                                    <c:if test="${critc:isP('SysResourceAdd')}">
                                        <button type="button" class="btn btn-success btn-sm" id="btnAdd">
                                            <i class="ace-icon fa fa-plus bigger-110"></i>新增
                                        </button>
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <table id="treeTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th width=150>模块名称</th>
                    <th width=120>模块代码</th>
                    <th>模块链接</th>
                    <th width=80>链接目标</th>
                    <th width=80>图标</th>
                    <th style="text-align: center;" width=80>排序</th>
                    <th width="241">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list }" var="resource" varStatus="st">
                    <tr id="${resource.id}" pId="${resource.parentId ne 1?resource.parentId:'0'}">
                        <td>${resource.name}</td>
                        <td>${resource.code}</td>
                        <td style="word-break: break-all;">${resource.url}</td>
                        <td>${resource.target}</td>
                        <td>
                            <div>
                                <i class="fa ${resource.iconImg}"></i>
                            </div>
                        </td>
                        <td style="text-align: center;">${resource.displayOrder}</td>
                        <td>
                            <c:if test="${critc:isP('SysResourceAdd')}">
                                <a href="toUpdate.htm?id=${resource.id}&backUrl=${backUrl}"> 修改</i>
                                </a>
                            </c:if> <c:if test="${critc:isP('SysResourceDelete')}">
                            <a href="javascript:delModule(${resource.id });"> 删除 </a>
                        </c:if>
                            <a href="${dynamicServer }/sys/resource/functionIndex.htm?parentId=${resource.id }">功能设置 </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">${ pageNavigate.pageModel}</div>
    </div>
</div>

</body>
<critc-script>
    <script src="${staticServer }/assets/treetable/jquery.treeTable.min.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(function() {
            $("#btnSearch").bind('click', searchModule);
            $("#btnAdd").bind('click', addUser);
            $("#btnClear").bind('click', clearCache);

            $("#treeTable").treeTable({
                expandLevel : 3
            });
        })

        // 查询方法
        var searchModule = function() {
            var url = "index.htm?";
            window.location = encodeURI(url);
        }
        // 删除
        var delModule = function(id) {
            bootbox.confirm("你确定要删除该模块吗？", function(result) {
                if (result) {
                    window.location = "delete.htm?id=" + id + "&backUrl=${backUrl}";
                }
            })
        }
        //新增
        var addUser = function(id) {
            window.location = 'toAdd.htm?backUrl=${backUrl }';
        }
        //清空缓存
        var clearCache = function() {
            window.location = 'clearCache.htm?backUrl=${backUrl }';
        }
    </script>
</critc-script>