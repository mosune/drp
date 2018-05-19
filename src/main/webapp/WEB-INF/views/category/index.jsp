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
                    <div class="col-sm-6 m-b-xs">
                        <div class="input-group">
                            <input type="text" id="nameLike" placeholder="名称" class="input-sm form-control"> <span class="input-group-btn">
                                        <button type="button" onclick="search();" class="btn btn-sm btn-primary"> 搜索</button> </span>
                        </div>
                    </div>
                </div>
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
                                    <label class="col-sm-4 control-label" for="name"><span style="color:red;">*</span>名称：</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="name" name="name" type="text" />
                                    </div>
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

    <input type="hidden" id="hideValue" />
</body>
<script src="<%=root %>/resources/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=root %>/resources/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=root %>/resources/js/jquery.extends.js"></script>
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
            queryParams: queryParams,
            sortable: true,
            idField: 'id',
            columns: [
                {field: 'name',width: '15%', title: '名称', align: 'center'},
                {field: 'remark',width: '20%', title: '描述', align: 'center'},
                {field: 'status',width: '10%', title: '状态', align: 'center',
                    formatter : function(value) {
                        if (value == 1) return "正常";
                        else return "未启用";
                    }},
                {field: 'createTime',width: '10%', title: '创建时间', align: 'center',
                    formatter : function(value) {
                        return $(this).dateFormat(value, 'yyyy-MM-dd HH:mm:ss');
                    }},
                {field: 'opt',width: '10%', title: '操作', align: 'center',
                    formatter: function(value, row){
                        return '<button type="button" class="btn btn-info btn-xs" onclick="openModel(\''+row.id+'\')">编辑</button>&nbsp;&nbsp;'
                            + '<button type="button" class="btn btn-danger btn-xs" onclick="deleteData(\''+row.id+'\')">删除</button>&nbsp;&nbsp;'
                            + '<button type="button" class="btn btn-primary btn-xs" onclick="updateStatus(\''+row.id+'\', \''+row.status+'\')">状态</button>';
                    }}
            ],
            toolbar: '#toolbar'
        });
    });

    function queryParams(params) {
        params.nameLike = $('#nameLike').val();
        return params;
    }

    function search() {
        _categoryTable.bootstrapTable("refresh");
    }

    function openModel(id) {
        if (id) {
            $("#hideValue").val(id);
            $.ajax({
                url: "<%=root%>category/getData.do",
                type: "post",
                data: {
                    id:id
                },
                dataType: "json",
                success:function(result) {
                    if (result.msg) {
                        toastr.error(result.msg);
                        return;
                    } else {
                        $("#name").val(result.category.name);
                        $("#desc").val(result.category.remark);
                    }
                }
            });
        } else {
            $("#hideValue").val("");
            $("#name").val("");
            $("#desc").val("");
        }
        $("#modal").modal("show");
    }
    
    function saveOrUpdate() {
        var name = $("#name").val();
        var remark = $("#desc").val();
        var id = $("#hideValue").val();
        $.ajax({
            url: "<%=root%>category/addOrUpdate.do",
            type: "post",
            data: {
                name:name,
                remark:remark,
                id:id
            },
            dataType: "json",
            success:function(result) {
                if (result.msg) {
                    toastr.error(result.msg);
                } else {
                    toastr.info("操作成功");
                    _categoryTable.bootstrapTable("refresh");
                    $('#modal').modal('hide');
                }
            }
        });
    }

    function deleteData(id) {
        swal({
            title: "确认删除么？",
            text: "要慎重啊！这个操作可是不能反悔的",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: false,
            showLoaderOnConfirm: true
        },
        function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url: "<%=root%>category/delete.do",
                    type: "post",
                    data: {id:id},
                    success:function(result){
                        if (result.msg) {
                            swal("取消", result.msg, "error");
                        } else {
                            swal("成功!", "您删除了这个类目", "success");
                            _categoryTable.bootstrapTable("refresh");
                        }
                    }
                })
            } else {
                swal("取消", "谢谢您的考虑:)", "error");
            }
        });
    }

    function updateStatus(id, status) {
        swal({
            title: "确认修改状态么？",
            text: "要慎重啊！这个操作可是不能反悔的",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "修改",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: false,
            showLoaderOnConfirm: true
        },
        function(isConfirm){
            if (isConfirm) {
                $.ajax({
                    url: "<%=root%>category/updateStatus.do",
                    type: "post",
                    data: {
                        id:id,
                        status:status
                    },
                    success:function(result){
                        if (result.msg) {
                            swal("取消", result.msg, "error");
                        } else {
                            swal("成功!", "您修改了状态", "success");
                            _categoryTable.bootstrapTable("refresh");
                        }
                    }
                })
            } else {
                swal("取消", "谢谢您的考虑:)", "error");
            }
        });
    }
</script>
</html>