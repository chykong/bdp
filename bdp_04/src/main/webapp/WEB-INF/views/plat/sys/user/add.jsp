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
    <div class="page-header">
        <h1>
            用户管理
            <small><i class="ace-icon fa fa-angle-double-right"></i> 新增用户
            </small>
        </h1>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <form id="userForm" name="userForm" class="form-horizontal" action="add.htm" method="post">
                <input type="hidden" name="backUrl" value="${backUrl }">
                <div class="form-group">
                    <label class="col-sm-3 control-label">账号：</label>
                    <div class="col-sm-9">
                        <input id="username" name="username" type="text" class="col-xs-10 col-sm-5" placeholder=""
                               value=""> <label
                            id="usernameTip"></label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">姓名：</label>
                    <div class="col-sm-9">
                        <input id="realname" type="text" name="realname" class="col-xs-10 col-sm-5" placeholder=""
                               value=""><label
                            id="realnameTip"></label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">手机：</label>
                    <div class="col-sm-9">
                        <input id="mobile" type="text" name="mobile" class="col-xs-10 col-sm-5" placeholder=""
                               value=""><label id="mobileTip"></label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">角色：</label>
                    <div class="col-sm-9 ">
                        <form:select path="sysUser.roleId" name="roleId" class="col-xs-10 col-sm-5" id="roleId">
                            <option value="">请选择角色</option>
                            <form:options items="${listRole }" itemValue="id" itemLabel="name"/>
                        </form:select>
                        <label id="roleIdTip"></label>
                    </div>
                </div>
                <div class="clearfix form-actions">
                    <div class="col-md-offset-3 col-md-9">
                        <button class="btn btn-primary" type="submit">
                            <i class="ace-icon fa fa-save bigger-110"></i> 保存
                        </button>
                        <button class="btn" type="button" onclick="history.back(-1)">
                            <i class="ace-icon fa fa-undo bigger-110"></i> 取消
                        </button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#userForm").validate({
            errorElement: "label",
            errorClass: "valiError",
            errorPlacement: function (error, element) {
                error.appendTo($("#" + element.attr('id') + "Tip"));
            },
            rules: {
                username: {
                    required: true,
                    minlength: 4,
                    maxlength: 20,
                    remote: {
                        url: "checkUserExist.htm", //后台处理程序
                        type: "post", //数据发送方式
                        //dataType: "json", //接受数据格式
                        data: { //要传递的数据
                            username: function () {
                                return $("#username").val();
                            }
                        }
                    }
                },
                realname: {
                    required: true,
                    maxlength: 20
                },
                mobile: {
                    telephone:true,
                    required: true,
                    maxlength: 11
                },
                roleId: {
                    required: true
                }
            },
            messages: {
                username: {
                    remote: "账号已存在！"
                }
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
    });
</script>
</body>
