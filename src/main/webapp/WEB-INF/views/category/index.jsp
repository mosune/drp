<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../base.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<%=root %>/resources/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <title>书本分类管理</title>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>书本分类管理</h5>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div id="toolbar">
                        <button type="button" onclick="openModel();" class="btn btn-primary btn-sm">添加</button>
                    </div>
                    <div class="col-sm-12">
                        <table class="table table-striped table-bordered table-hover" id="categoryTable">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" style="display: none;" data-keyboard="false" data-backdrop="static" id="modal" aria-hidden="true" role="dialog" aria-labelledby="modalHeader">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <form id="myForm" class="form-horizontal">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="name">名称：</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="name" name="name" type="text" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <label class="col-sm-4 control-label" for="topLevel">是否为第一级：</label>
                                <div class="radio col-sm-8">
                                    <label><input type="radio" value="true" id="true" checked name="chooseFirst">是</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <label><input type="radio" value="false" id="false" name="chooseFirst">否</label>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <label class="col-sm-4 control-label" for="topLevel">上一级别：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="topLevel" id="topLevel" disabled>
                                        <option value="0">请选择</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <label class="col-sm-2 control-label" for="desc">描述：</label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" id="desc"></textarea>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info" onclick="saveOrUpdate();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="closeModal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="<%=root %>/resources/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=root %>/resources/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript">
    var _categoryTable;
    $(document).ready(function() {
        _categoryTable = $('#categoryTable').bootstrapTable({
            sidePagination:'server',//设置为服务器端分页
            url: '<%=root%>/category/list.do',
            method: 'post',
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            striped: true,
            pagination: true,
            pageList: [10,30,50],
            showToggle: true,
            showRefresh: true,
            showColumns: true,
            sortable: true,
            idField: 'id',
            columns: [
                {field: 'name',width: '15%', title: '名称', align: 'center'},
                {field: 'level', width: '10%', title: '级别', align: 'center'},
                {field: 'desc',width: '20%', title: '描述', align: 'center'},
                {field: 'status',width: '10%', title: '状态', align: 'center'},
                {field: 'createTime',width: '10%', title: '创建时间', align: 'center'}
            ],
            toolbar: '#toolbar'
        });

        $('input[type=radio][name=chooseFirst]').change(function() {
            if (this.value == 'true') {
                $("#topLevel option:first").prop("selected", 'selected');
                $("#topLevel").attr("disabled", true);
            } else {
                $("#topLevel").attr("disabled", false);
                $.ajax({
                    url: "<%=root%>category/getTopLevel.do",
                    type: "post",
                    dataType: "json",
                    success:function(result) {

                    }
                });
            }
        });
    });

    function openModel() {
        $("#name").val("");
        $("#topLevel option:first").prop("selected", 'selected');
        $("#desc").val("");
        $("#modal").modal("show");
    }
    
    function saveOrUpdate() {
        var name = $("#name").val();
        var level = $("#topLevel").val();
        var remark = $("#desc").val();
        $.ajax({
            url: "<%=root%>category/addOrUpdate.do",
            type: "post",
            data: {
                name:name,
                level:level,
                remark:remark
            },
            dataType: "json",
            success:function(result) {
                if (result.msg) {
                    toastr.error(result.msg);
                } else {
                    toastr.info("操作成功");
                }
            }
        });
    }
</script>
</html>