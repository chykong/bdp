<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../../common/taglib.jsp" %>

<head>
    <title>用户管理</title>
</head>

<body>
<div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
    <ul class="breadcrumb">
        <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
        <li class="active">系统管理</li>
        <li class="active">用户管理</li>
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
                                <td>账号：</td>
                                <td><input type="text" id="txtUsername" class="form-control input-small"
                                           placeholder=""
                                           value="${sysUserSearchVO.username }"></td>
                                <td>姓名：</td>
                                <td><input type="text" id="txtRealname" class="form-control input-middle"
                                           placeholder=""
                                           value="${sysUserSearchVO.realname }"></td>
                                <td>状态：</td>
                                <td><form:select path="sysUserSearchVO.status" class="form-control"
                                                 id="cmbStatus">
                                    <form:option value="" label="--全部--"/>
                                    <form:option value="1">正常</form:option>
                                    <form:option value="2">已锁定</form:option>
                                </form:select></td>
                                <td>角色：</td>
                                <td><form:select path="sysUserSearchVO.roleId" class="form-control"
                                                 id="cmbRoleId">
                                    <form:option value="" label="--全部--"/>
                                    <form:options items="${listRole}" itemValue="id" itemLabel="name"/>
                                </form:select></td>
                                <td>
                                    <button class="btn btn-primary btn-sm" id="btnSearch">
                                        <i class="ace-icon fa fa-search"></i> 查询
                                    </button>
                                    <button type="button" class="btn btn-success btn-sm" id="btnAdd">
                                        <i class="ace-icon fa fa-plus bigger-110"></i>新增
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
                    <th width=120>账号</th>
                    <th width="120">姓名</th>
                    <th width="120">角色</th>
                    <th width="120">创建人</th>
                    <th width="140">创建时间</th>
                    <th width="80">状态</th>
                    <th width="80">登录记录</th>
                    <th width="160">操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${list }" var="sysUser" varStatus="st">
                    <tr>
                        <td>${st.index+1 }</td>
                        <td>${sysUser.username }</td>
                        <td>${sysUser.realname }</td>
                        <td>${sysUser.roleName }</td>
                        <td>${sysUser.createdBy }</td>
                        <td><fmt:formatDate value="${sysUser.createdAt }"
                                            pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>${critc:getUserStatus(sysUser.status)}</td>
                        <td>
                            <a href="javascript:viewLoginHis('${sysUser.id}','${sysUser.username}')">查看 </a>
                        </td>
                        <td>
                            <a href="toUpdate.htm?id=${sysUser.id }&backUrl=${backUrl}"> 修改 </a>
                            <a href="javascript:delUser(${sysUser.id });"> 删除 </a>
                            <c:if test="${sysUser.status==1}">
                                <a href="javascript:lock(${sysUser.id });">锁定 </a>
                            </c:if> <c:if test="${sysUser.status==2}">
                            <a href="javascript:unlock(${sysUser.id });">解锁 </a>
                        </c:if>
                            <a href="javascript:resetPass(${sysUser.id });">重置密码 </a>
                        </td>
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
    <div id="dialog-viewLogin" class="hide center">
    </div>
</div>

</body>
<critc-script>
    <script type="text/javascript">
        $(function () {
            $("#btnSearch").bind('click', searchUser);
            $("#btnAdd").bind('click', addUser);
        })

        // 查询方法
        var searchUser = function () {
            var url = "index.htm?";
            if ($("#txtUsername").val() != '')
                url += "username=" + $("#txtUsername").val();
            if ($("#txtRealname").val() != '')
                url += "&realname=" + $("#txtRealname").val();
            if ($("#cmbStatus").val() != '')
                url += "&status=" + $("#cmbStatus").val();
            if ($("#cmbRoleId").val() != '')
                url += "&role_id=" + $("#cmbRoleId").val();
            window.location = encodeURI(url);
        }
        // 删除
        var delUser = function (id) {
            bootbox.confirm("你确定要删除该用户吗？", function (result) {
                if (result) {
                    window.location = "delete.htm?id=" + id + "&backUrl=${backUrl}";
                }
            })
        }
        // 锁定
        var lock = function (id) {
            bootbox.confirm("你确定要锁定该用户吗？", function (result) {
                if (result) {
                    window.location = "saveLock.htm?id=" + id + "&backUrl=${backUrl}";
                }
            })
        }
        // 解锁
        var unlock = function (id) {
            bootbox.confirm("你确定要解锁该用户吗？", function (result) {
                if (result) {
                    window.location = "saveUnlock.htm?id=" + id + "&backUrl=${backUrl}";
                }
            })
        }
        // 重置密码
        var resetPass = function (id) {
            bootbox.confirm("你确定要给该用户重置密码吗？", function (result) {
                if (result) {
                    window.location = "saveResetPass.htm?id=" + id + "&backUrl=${backUrl}";
                }
            })
        }

        //新增
        var addUser = function (id) {
            window.location = 'toAdd.htm?backUrl=${backUrl }';
        }

        var viewLoginHis = function (id, title) {
            $.post('searchUserLogin.htm', {
                userId: id
            }, function (html) {
                $("#dialog-viewLogin").html(html);
                var dialog = $("#dialog-viewLogin").removeClass('hide').dialog({
                    title: "【" + title + "】登录历史",
                    title_html: false,
                    width: 1000,
                    height:400,
                    minHeight: 400,
                    position: {my: "center", at: "center", of: window},
                    modal: true,
                    buttons: [
                        {
                            text: "返回",
                            "class": "btn btn-minier btn-center",
                            click: function () {
                                $(this).dialog("close");
                            }
                        }
                    ]
                });
            });
        }
    </script>
</critc-script>