<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>${webTitle}</title>
    <%@ include file="common/styles.jspf" %>
    <script src="${staticServer }/assets/ace1.4/js/userBrower.js"></script>
    <script src="${staticServer }/assets/ace1.4/js/jCookie.js"></script>
</head>

<body class="login-layout">
<div class="main-container login-main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-wechat green"></i> <b>${webTitle}-登录</b>
                                    </h4>
                                    <div class="space-6"></div>
                                    <form id="loginForm" action="checkLogin.htm" method="post">
                                        <fieldset>
                                            <label class="block clearfix"> <span
                                                    class="block input-icon input-icon-right">
                                                <input type="text" class="form-control" name="username" id="username"
                                                       placeholder="用户名"/>
                                                <i class="ace-icon fa fa-user"></i>
												</span>
                                            </label> <label class="block clearfix"> <span
                                                class="block input-icon input-icon-right"> <input type="password"
                                                                                                  class="form-control"
                                                                                                  name="password"
                                                                                                  id="password"
                                                                                                  placeholder="密码"/> <i
                                                class="ace-icon fa fa-lock"></i>
												</span>
                                        </label> <label class="block clearfix"> <span
                                                class="block input-icon input-icon-right"> <span id="lblMessage"
                                                                                                 class="errMsg"
                                                                                                 style="display: none"> 账号或密码输入错误！ </span>
												</span>
                                            <div class="space"></div>
                                            <div class="clearfix">
                                                <label class="inline"> <input type="checkbox" class="ace" id="chk"/>
                                                    <span class="lbl"> 记住我</span>
                                                </label>
                                                <button type="submit"
                                                        class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="ace-icon fa fa-key"></i> <span
                                                        class="bigger-110">登录</span>
                                                </button>
                                            </div>
                                            <div class="space-4"></div>
                                            <br/> <label class=" clearfix"> <span
                                                    class="block input-icon ">
															<span class="inline input-icon input-icon-right">版权所有 © 2017 <a
                                                                    href="http://#" target="_blank">技术研发中心</a>
                                                            </span>
														</span></label>
                                        </fieldset>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="common/scripts.jspf" %>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function ($) {

        $("#loginForm").validate({
            errorElement: "label",
            errorClass: "valiError",
            errorPlacement: function (error, element) {
            },
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                }
            },
            messages: {
                username: {
                    required: ""
                },
                password: {
                    required: ""
                }
            },
            submitHandler: function (form) {
                checkLogin();
            }
        });

        var cookie_chk = jQuery.jCookie('bls_chk');
        if (cookie_chk != '' && cookie_chk != null && cookie_chk == '1') {
            $('#chk').prop("checked", true);
            $('#username').val(jQuery.jCookie('bls_username'));
        }
    });

    function checkLogin() {
        if ($('#chk').is(':checked')) {
            jQuery.jCookie('bls_chk', '1', 30);
            jQuery.jCookie('bls_username', $('#username').val(), 30);
        } else {
            jQuery.jCookie('bls_chk', '0', 30);
        }

        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            type: "post",
            url: "${dynamicServer}/checkLogin.htm",
            data: {
                username: username,
                password: password,
                terminal: getUserTerminalType(),
                explorerType: getExplorerInfo().browser,
                explorerVersion: getExplorerInfo().version
            },
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    $("#lblMessage").hide();
                    window.location.href = "${dynamicServer}/index.htm";
                } else {
                    $("#lblMessage").html(data.msgText);
                    $("#lblMessage").show();
                }
            },
            error: function (data) {
                $("#lblMessage").html('登录失败');
                $("#lblMessage").show();
            }
        });
    }

</script>
</body>
</html>