<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../base.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<%=root %>/resources/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <title>图书出库管理</title>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>图书出库管理</h5>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">全部出库</a>
                            </li>
                            <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false">待退货出库</a>
                            </li>
                            <li class=""><a data-toggle="tab" href="#tab-3" aria-expanded="false">待销售出库</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-6 m-b-xs">
                                            <div class="input-group">
                                                <input type="text" id="nameLike1" placeholder="名称" class="input-sm form-control"> <span class="input-group-btn">
                                        <button type="button" onclick="search1();" class="btn btn-sm btn-primary">搜索</button> </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="table table-striped table-bordered table-hover" id="orderAllTable">
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="tab-2" class="tab-pane">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-6 m-b-xs">
                                            <div class="input-group">
                                                <input type="text" id="nameLike2" placeholder="名称" class="input-sm form-control"> <span class="input-group-btn">
                                        <button type="button" onclick="search2();" class="btn btn-sm btn-primary">搜索</button> </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="table table-striped table-bordered table-hover" id="orderDingTable">
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="tab-3" class="tab-pane">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-sm-6 m-b-xs">
                                            <div class="input-group">
                                                <input type="text" id="nameLike3" placeholder="名称" class="input-sm form-control"> <span class="input-group-btn">
                                                <button type="button" onclick="search3();" class="btn btn-sm btn-primary">搜索</button> </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <table class="table table-striped table-bordered table-hover" id="orderCaiTable">
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                            <table class="table table-striped table-bordered table-hover" id="goodTable">
                            </table>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="closeModal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="<%=root %>/resources/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=root %>/resources/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=root %>/resources/js/jquery.extends.js"></script>
<script type="text/javascript">
    var _orderDingTable;
    var _orderCaiTable;
    var _orderAllTable;

    $(document).ready(function() {
        _orderAllTable = $('#orderAllTable').bootstrapTable({
            sidePagination:'server',//设置为服务器端分页
            url: '<%=root%>/order/list.do',
            method: 'post',
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            striped: true,
            pagination: true,
            pageList: [10,30,50],
            showToggle: true,
            showRefresh: true,
            showColumns: true,
            queryParams: queryParams1,
            sortable: true,
            idField: 'id',
            columns: [
                {field: 'number',width: '15%', title: '单号', align: 'center'},
                {field: 'totalPrice', width: '10%', title: '总金额（元）', align: 'center'},
                {field: 'status',width: '10%', title: '状态', align: 'center',
                    formatter : function(value) {
                        if (value === 4) return "采购退货";
                        else if(value === 5) return "采购退货成功";
                        else if(value === 6) return "退货取消";
                        else if(value === 11) return "待出库";
                        else if(value === 12) return "已出库";
                        else if(value === 13) return "销售已取消";
                    }},
                {field: 'createTime',width: '10%', title: '创建时间', align: 'center',
                    formatter : function(value) {
                        return $(this).dateFormat(value, 'yyyy-MM-dd HH:mm:ss');
                    }},
                {field: 'opt',width: '10%', title: '操作', align: 'center',
                    formatter: function(value, row){
                        return '<button type="button" class="btn btn-info btn-xs" onclick="openModel(\''+row.id+'\')">详情</button>';
                    }}
            ],
            toolbar: '#toolbar'
        });
    });

    $(document).ready(function() {
        _orderDingTable = $('#orderDingTable').bootstrapTable({
            sidePagination:'server',//设置为服务器端分页
            url: '<%=root%>/order/list.do',
            method: 'post',
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            striped: true,
            pagination: true,
            pageList: [10,30,50],
            showToggle: true,
            showRefresh: true,
            showColumns: true,
            queryParams: queryParams2,
            sortable: true,
            idField: 'id',
            columns: [
                {field: 'number',width: '15%', title: '单号', align: 'center'},
                {field: 'totalPrice', width: '10%', title: '总金额（元）', align: 'center'},
                {field: 'status',width: '10%', title: '状态', align: 'center',
                    formatter : function(value) {
                        if (value === 4) return "采购退货";
                        else if(value === 5) return "采购退货成功";
                        else if(value === 6) return "退货取消";
                    }},
                {field: 'createTime',width: '10%', title: '创建时间', align: 'center',
                    formatter : function(value) {
                        return $(this).dateFormat(value, 'yyyy-MM-dd HH:mm:ss');
                    }},
                {field: 'opt',width: '10%', title: '操作', align: 'center',
                    formatter: function(value, row){
                        return '<button type="button" class="btn btn-info btn-xs" onclick="openModel(\''+row.id+'\')">详情</button>&nbsp;&nbsp;' +
                            '<button type="button" class="btn btn-primary btn-xs" onclick="outDepot(\''+row.id+'\', 1)">出库</button>&nbsp;&nbsp;'
                            + '<button type="button" class="btn btn-danger btn-xs" onclick="deleteData(\''+row.id+'\', 1)">取消</button>&nbsp;&nbsp;';
                    }}
            ],
            toolbar: '#toolbar'
        });
    });

    $(document).ready(function() {
        _orderCaiTable = $('#orderCaiTable').bootstrapTable({
            sidePagination:'server',//设置为服务器端分页
            url: '<%=root%>/order/list.do',
            method: 'post',
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            striped: true,
            pagination: true,
            pageList: [10,30,50],
            showToggle: true,
            showRefresh: true,
            showColumns: true,
            queryParams: queryParams3,
            sortable: true,
            idField: 'id',
            columns: [
                {field: 'number',width: '15%', title: '单号', align: 'center'},
                {field: 'totalPrice', width: '10%', title: '总金额（元）', align: 'center'},
                {field: 'status',width: '10%', title: '状态', align: 'center',
                    formatter : function(value) {
                        if (value === 11) return "待出库";
                        else if(value === 12) return "已出库";
                        else if(value === 13) return "销售已取消";
                    }},
                {field: 'createTime',width: '10%', title: '创建时间', align: 'center',
                    formatter : function(value) {
                        return $(this).dateFormat(value, 'yyyy-MM-dd HH:mm:ss');
                    }},
                {field: 'opt',width: '10%', title: '操作', align: 'center',
                    formatter: function(value, row){
                        return '<button type="button" class="btn btn-info btn-xs" onclick="openModel(\''+row.id+'\')">详情</button>&nbsp;&nbsp;' +
                            '<button type="button" class="btn btn-primary btn-xs" onclick="outDepot(\''+row.id+'\')">出库</button>&nbsp;&nbsp;'
                            + '<button type="button" class="btn btn-danger btn-xs" onclick="deleteData(\''+row.id+'\')">取消</button>&nbsp;&nbsp;';
                    }}
            ],
            toolbar: '#toolbar'
        });
    });

    function queryParams1(params) {
        params.nameLike = $('#nameLike1').val();
        var dataArr = new Array();
        dataArr.push(4);
        dataArr.push(5);
        dataArr.push(11);
        dataArr.push(12);
        params.status = dataArr;
        return params;
    }

    function queryParams2(params) {
        params.nameLike = $('#nameLike2').val();
        var dataArr = new Array();
        dataArr.push(4);
        params.status = dataArr;
        return params;
    }

    function queryParams3(params) {
        params.nameLike = $('#nameLike3').val();
        var dataArr = new Array();
        dataArr.push(11);
        params.status = dataArr;
        return params;
    }

    function search1() {
        _orderAllTable.bootstrapTable("refresh");
    }

    function search2() {
        _orderDingTable.bootstrapTable("refresh");
    }

    function search3() {
        _orderCaiTable.bootstrapTable("refresh");
    }

    function outDepot(id, type) {
        swal({
            title: "确认出库么？",
            text: "要慎重啊！这个操作可是不能反悔的",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "出库",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: false,
            showLoaderOnConfirm: true
        },
        function(isConfirm){
            if (isConfirm) {
                if (type == 1) type = 'out';
                else type = 'back_out';
                $.ajax({
                    url: "<%=root%>goodsStock/purchaseIn.do",
                    type: "post",
                    data: {
                        orderId:id,
                        type:type
                    },
                    success:function(result){
                        if (result.msg) {
                            swal("取消", result.msg, "error");
                        } else {
                            swal("成功!", "出库成功", "success");
                            _orderDingTable.bootstrapTable("refresh");
                            _orderCaiTable.bootstrapTable("refresh");
                        }
                    }
                })
            } else {
                swal("取消", "谢谢您的考虑:)", "error");
            }
        });
    }

    function deleteData(id, type) {
        swal({
            title: "确认取消么？",
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
                if (type == 1) type = 'out';
                else type = 'back_out';
                $.ajax({
                    url: "<%=root%>goodsStock/delete.do",
                    type: "post",
                    data: {orderId:id,
                        type:type},
                    success:function(result){
                        if (result.msg) {
                            swal("取消", result.msg, "error");
                        } else {
                            swal("成功!", "您取消了这个订单", "success");
                            _orderDingTable.bootstrapTable("refresh");
                            _orderCaiTable.bootstrapTable("refresh");
                        }
                    }
                })
            } else {
                swal("取消", "谢谢您的考虑:)", "error");
            }
        });
    }

    function openModel(id) {
        $("#modal").modal("show");
        var _goodTable;
        _goodTable = $('#goodTable').bootstrapTable({
            sidePagination:'server',//设置为服务器端分页
            url: '<%=root%>/order/getGoods.do',
            method: 'post',
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            striped: true,
            queryParams : function(params) {
                params.id = id;
                return params;
            },
            columns: [
                {field: 'goodsName',width: '40%', title: '货物名称', align: 'center'},
                {field: 'num',width: '30%', title: '数量', align: 'center'},
                {field: 'originalPrice',width: '30%', title: '成本价（元）', align: 'center'}
            ],
        });
    }

    $("#modal").on("hidden.bs.modal", function() {
        $("#goodTable").bootstrapTable('destroy');
    });
</script>
</html>