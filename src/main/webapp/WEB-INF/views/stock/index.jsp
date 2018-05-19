<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../base.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<%=root %>/resources/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <title>图书进销存对账</title>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>图书进销存对账</h5>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="col-sm-6 m-b-xs">
                        <div class="input-group">
                            <input type="text" id="nameLike" placeholder="商品名称" class="input-sm form-control"> <span class="input-group-btn">
                                        <button type="button" onclick="search();" class="btn btn-sm btn-primary"> 搜索</button> </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table-striped table-bordered table-hover" id="stockTable">
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
    <input type="hidden" id="hideValue" />
</body>
<script src="<%=root %>/resources/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=root %>/resources/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=root %>/resources/js/jquery.extends.js"></script>
<script type="text/javascript">
    var _stockTable;
    $(document).ready(function() {
        _stockTable = $('#stockTable').bootstrapTable({
            sidePagination:'server',//设置为服务器端分页
            url: '<%=root%>/goodsStock/list.do',
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
                {field: 'goodsName',width: '10%', title: '商品名称', align: 'center'},
                {field: 'cateName',width: '10%', title: '品类名称', align: 'center'},
                {field: 'originalStock',width: '10%', title: '原始库存', align: 'center'},
                {field: 'currentStock',width: '10%', title: '当前库存', align: 'center'},
                {field: 'inQuantity',width: '10%', title: '入库数量', align: 'center'},
                {field: 'outQuentity',width: '10%', title: '出库数量', align: 'center'},
                {field: 'originalPrice',width: '10%', title: '成本价（元）', align: 'center'},
                {field: 'salePrice',width: '10%', title: '售卖价（元）', align: 'center'},
                {field: 'price',width: '10%', title: '利润（元）', align: 'center'},
                {field: 'opt',width: '10%', title: '操作', align: 'center',
                    formatter: function(value, row){
                        return '<button type="button" class="btn btn-info btn-xs" onclick="openModel(\''+row.goodsId+'\')">详情</button>';
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
        _stockTable.bootstrapTable("refresh");
    }

    var _goodTable;
    function openModel(id) {
        $("#modal").modal("show");
        _goodTable = $('#goodTable').bootstrapTable({
            sidePagination:'server',//设置为服务器端分页
            url: '<%=root%>/goodsStockLog/list.do',
            method: 'post',
            contentType: 'application/x-www-form-urlencoded;charset=utf-8',
            striped: true,
            queryParams : function(params) {
                params.id = id;
                return params;
            },
            columns: [
                {field: 'quantity',width: '30%', title: '数量', align: 'center'},
                {field: 'type',width: '30%', title: '类型', align: 'center',
                    formatter : function(value) {
                        if (value == 'in') return "采购入库";
                        else if (value == 'back_in') return "退货入库";
                        else if (value == 'out') return "退货出库";
                        else return "销售出库"
                    }},
                {field: 'createTime',width: '40%', title: '操作时间', align: 'center',
                    formatter : function(value) {
                        return $(this).dateFormat(value, 'yyyy-MM-dd HH:mm:ss');
                    }}
            ],
        });
    }

    $("#modal").on("hidden.bs.modal", function() {
        $("#goodTable").bootstrapTable('destroy');
    });
</script>
</html>