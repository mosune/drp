<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../base.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<%=root %>/resources/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <title>员工管理</title>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>员工管理</h5>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div id="toolbar">
                        <button type="button" onclick="openModel();" class="btn btn-primary btn-sm">添加</button>
                    </div>
                    <div class="col-sm-12">
                        <table class="table table-striped table-bordered table-hover" id="userTable">
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
                                    <label class="col-sm-4 control-label" for="account">账号名：</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="account" name="account" type="text" />
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <label class="col-sm-4 control-label" for="shopId">门店：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="shopId" id="shopId">
                                        <option value="0">请选择</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <label class="col-sm-4 control-label" for="roleId">岗位：</label>
                                <div class="col-sm-8">
                                    <select class="form-control m-b" name="roleId" id="roleId">
                                        <option value="0">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="name">姓名：</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="name" name="name" type="text"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label" for="mobile">电话：</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" id="mobile" name="mobile" type="text"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info" onclick="save();">保存</button>
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
    var _userTable;
    $(document).ready(function() {
        _userTable = $('#userTable').bootstrapTable({
            sidePagination:'server',//设置为服务器端分页
            url: '<%=root%>/adminUser/list.do',
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
                {field: 'account',width: '10%', title: '账号', align: 'center'},
                {field: 'name',width: '15%', title: '姓名', align: 'center'},
                {field: 'shopName',width: '5%', title: '门店', align: 'center'},
                {field: 'roleName',width: '5%', title: '角色', align: 'center'},
                {field: 'mobile', width: '5%', title: '电话', align: 'center'},
                {field: 'status',width: '5%', title: '状态', align: 'center',
                    formatter : function(value) {
                        if (value === '0') return "已启用";
                        else return "未启用";
                    }},
                {field: 'createTime',width: '15%', title: '创建时间', align: 'center',
                    formatter : function(value) {
                        return $(this).dateFormat(value, 'yyyy-MM-dd HH:mm:ss');
                    }},
                {field: 'opt',width: '20%', title: '操作', align: 'center',
                    formatter: function(value, row){
                        return '<button type="button" class="btn btn-info btn-xs" onclick="openModel(\''+row.id+'\')">编辑</button>&nbsp;&nbsp;'
                            + '<button type="button" class="btn btn-danger btn-xs" onclick="deleteData(\''+row.id+'\')">删除</button>&nbsp;&nbsp;'
                            + '<button type="button" class="btn btn-primary btn-xs" onclick="updateStatus(\''+row.id+'\')">状态</button>';
                    }}
            ],
            toolbar: '#toolbar'
        });
    });

    function openModel(id) {
        $("#shopId").empty();
        $("#shopId").append("<option value='0'>请选择</option>");
        $("#roleId").empty();
        $("#roleId").append("<option value='0'>请选择</option>");
        $("#account").attr("disabled", false);
        $.ajax({
            url: "<%=root%>shop/getList.do",
            type: "post",
            async: false,
            dataType: "json",
            success: function (data) {
                for (var i = 0; i < data.list.length; i++) {
                    $("#shopId").append("<option value=" + data.list[i].shopNum + ">" + data.list[i].name + "</option>");
                }
            }
        });
        $.ajax({
            url: "<%=root%>role/getList.do",
            type: "post",
            async: false,
            dataType: "json",
            success: function (data) {
                for (var i = 0; i < data.list.length; i++) {
                    $("#roleId").append("<option value=" + data.list[i].id + ">" + data.list[i].name + "</option>");
                }
            }
        });
        if (id) {
            $.ajax({
                url: "<%=root%>adminUser/getData.do",
                type: "post",
                async: false,
                data: {id:id},
                dataType: "json",
                success: function (data) {
                    $("#account").val(data.user.account);
                    $("#name").val(data.user.name);
                    $("#mobile").val(data.user.mobile);
                    $("#shopId").find("option[value = "+data.user.shopId+"]").attr("selected","selected");
                    $("#roleId").find("option[value = "+data.user.roleId+"]").attr("selected","selected");
                }
            });
            $("#account").attr("disabled", true);
            $("#hideValue").val(id);
        } else {
            $("#account").val("");
            $("#name").val("");
            $("#mobile").val("");
            $("#hideValue").val("");
        }

        $("#modal").modal("show");
    }
    
    function save() {
        var account = $("#account").val();
        var name = $("#name").val();
        var mobile = $("#mobile").val();
        var shopId = $("#shopId").val();
        var roleId = $("#roleId").val();
        var id = $("#hideValue").val();
        var str;
        if (id) {
            str = "<%=root%>adminUser/update.do";
        } else {
            str = "<%=root%>adminUser/add.do";
        }
        $.ajax({
            url: str,
            type: "post",
            data: {
                account:account,
                name:name,
                mobile:mobile,
                shopId:shopId,
                roleId:roleId,
                id:id
            },
            dataType: "json",
            success: function (data) {
                if (data.msg) {
                    toastr.error(data.msg);
                } else {
                    toastr.info("操作成功");
                    _userTable.bootstrapTable("refresh");
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
                    url: "<%=root%>adminUser/delete.do",
                    type: "post",
                    data: {id:id},
                    success:function(result){
                        if (result.msg) {
                            swal("取消", result.msg, "error");
                        } else {
                            swal("成功!", "您删除了这个用户", "success");
                            _userTable.bootstrapTable("refresh");
                        }
                    }
                })
            } else {
                swal("取消", "谢谢您的考虑:)", "error");
            }
        });
    }

    function updateStatus(id) {
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
                    url: "<%=root%>adminUser/updateStatus.do",
                    type: "post",
                    data: {
                        id:id
                    },
                    success:function(result){
                        if (result.msg) {
                            swal("取消", result.msg, "error");
                        } else {
                            swal("成功!", "您修改了这个状态", "success");
                            _userTable.bootstrapTable("refresh");
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