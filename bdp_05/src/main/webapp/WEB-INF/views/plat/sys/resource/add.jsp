<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/taglib.jsp" %>

<head>
    <title>资源管理</title>
    <critc-css>
        <link href="${staticServer }/assets/zTree3.5/css/zTreeStyle/metro.css" rel="stylesheet" type="text/css"/>
    </critc-css>
</head>

<body>
<!-- #section:basics/content.breadcrumbs -->
<div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
    <ul class="breadcrumb">
        <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
        <li class="active">系统管理</li>
        <li class="active">资源管理</li>
    </ul>
</div>

<div class="page-content">
    <div class="page-header">
        <h1>
            资源管理
            <small><i class="ace-icon fa fa-angle-double-right"></i> 新增资源
            </small>
        </h1>
    </div>
    <!-- /.page-header -->

    <div class="column">
        <div class="col-xs-12">
            <form id="resouceForm" name="resouceForm" class="form-horizontal" action="add.htm" method="post">
                <input type="hidden" name="backUrl" value="${backUrl }">
                <div class="form-group">
                    <label class="col-sm-3 control-label">资源名称：</label>
                    <div class="col-sm-9">
                        <input id="name" name="name" type="text" class="col-xs-10 col-sm-5" placeholder=""
                               value=""> <label id="nameTip"></label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">类型：</label>
                    <div class="col-sm-9 ">
                        <select if="type" name="type" class="col-xs-10 col-sm-5">
                            <option value="1" <c:if test="${sysResource.type eq 1}"> selected </c:if>>模块</option>
                            <option value="2" <c:if test="${sysResource.type eq 2}"> selected </c:if>>功能</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">上级节点：</label>
                    <div class="col-sm-9">
                        <input id="parentId" type="hidden" name="parentId"
                               class="col-xs-10 col-sm-5" placeholder="" value="${sysResource.parentId}">
                        <input id="parentName" type="text" name="parentName" readonly="readonly"
                               class="col-xs-10 col-sm-5" placeholder="" value="${sysResource.parentName}">
                        <span class="input-group-btn">
											<button type="button" class="btn btn-primary btn-sm"
                                                    onclick="javascript:showSelTree()">
												<span class="ace-icon fa fa-search icon-on-right bigger-110"></span> 选择
											</button><label id="parentIdTip"></label>
										</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">资源代码：</label>
                    <div class="col-sm-9">
                        <input id="code" type="text" name="code" class="col-xs-10 col-sm-5" placeholder=""
                               value=""><label id="codeTip"></label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">资源链接：</label>
                    <div class="col-sm-9">
                        <input id="url" type="text" name="url" class="col-xs-10 col-sm-5" placeholder=""
                               value=""><label id="urlTip"></label>
                    </div>
                </div>
                <div class="form-group" id="divTarget">
                    <label class="col-sm-3 control-label">操作目标：</label>
                    <div class="col-sm-9 ">
                        <select name="target" class="col-xs-10 col-sm-5">
                            <option value="_self">_self</option>
                            <option value="_blank">_blank</option>
                        </select>
                    </div>
                </div>
                <div class="form-group" id="divIconImg">
                    <label class="col-sm-3 control-label">图标：</label>
                    <div class="col-sm-9">
                        <input id="iconImg" type="text" name="iconImg" class="col-xs-10 col-sm-5" placeholder=""
                               value=""> <span class="input-group-btn">
											<button type="button" class="btn btn-primary btn-sm"
                                                    onclick="javascript:showIcon()">
												<span class="ace-icon fa fa-search icon-on-right bigger-110"></span> 选择
											</button>
										</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">描述：</label>
                    <div class="col-sm-9">
                        <input id="description" type="text" name="description" class="col-xs-10 col-sm-5" placeholder=""
                               value=""><label id="descriptionTip"></label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">排序：</label>
                    <div class="col-sm-9">
                        <input id="displayOrder" type="text" name="displayOrder" class="col-xs-10 col-sm-5"
                               placeholder="" value=""><label
                            id="displayOrderTip"></label>
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
    <!-- /.main-content -->
</div>

<div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">选择上级节点</h4>
            </div>
            <div class="modal-body">
                <ul id="tree" class="ztree" style="width: 560px; overflow: auto;"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="javascript:getSelected();">确认</button>
                <button type="button" class="btn " data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="icon.jsp" %>


</body>
<critc-script>
    <script src="${staticServer }/assets/zTree3.5/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
    <script type="text/javascript">

        var zTree;
        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: ""
                }
            }
        };
        var zNodes = [${ztree}];
        jQuery(document).ready(function () {
            var t = $("#tree");
            t = $.fn.zTree.init(t, setting, zNodes);
            var zTree = $.fn.zTree.getZTreeObj("tree");
        });

        function getSelected() {
            var treeObj = $.fn.zTree.getZTreeObj("tree");
            var nodes = treeObj.getSelectedNodes();
            if (nodes.length > 0) {
                $("#parentId").val(nodes[0].id);
                $("#parentName").val(nodes[0].name);
                $('#basic').modal('hide');
            }
            else return;

        }

        function showSelTree() {
            $('#basic').modal('show');
        }
        function showIcon() {
            $('#iconModel').modal('show');
        }
        $(document).ready(function () {
            //如果为新增功能，则不显示图标和target
            var type = '${sysResource.type}';
            if (type == 2) {
                $("#divIconImg").hide();
                $("#divTarget").hide();
            }

            $("#resouceForm").validate({
                debug: true,
                errorElement: "label",
                errorClass: "valiError",
                errorPlacement: function (error, element) {
                    error.appendTo($("#" + element.attr('id') + "Tip"));
                },
                rules: {
                    name: {
                        required: true,
                        maxlength: 40
                    },
                    code: {
                        required: true,
                        maxlength: 40
                    },
                    url: {
                        required: true,
                        maxlength: 100
                    },
                    displayOrder: {
                        required: true,
                        number: true,
                        maxlength: 10
                    },
                    description: {
                        maxlength: 50
                    }
                },
                messages: {},
                submitHandler: function (form) {
                    form.submit();
                }
            });
        });

    </script>
</critc-script>