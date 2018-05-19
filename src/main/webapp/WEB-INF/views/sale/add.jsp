<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../base.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<%=root %>/resources/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <title>添加退货单</title>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>添加退货单</h5>
        </div>
        <div class="ibox-content">
            <div class="row">
                <div class="col-sm-6 m-b-xs">
                    <div class="input-group">
                        <input type="text" id="nameLike" placeholder="名称" class="input-sm form-control">
                        <span class="input-group-btn">
                            <button type="button" onclick="search();" class="btn btn-sm btn-primary">搜索</button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div id="toolbar">
                    <button type="button" onclick="addOrder();" class="btn btn-primary btn-sm">提交</button>
                </div>
                <div class="col-sm-12">
                    <table class="table table-striped table-bordered table-hover" id="goodsTable">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="<%=root %>/resources/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=root %>/resources/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=root %>/resources/js/jquery.extends.js"></script>
<script type="text/javascript">
    var _goodsTable;
    $(document).ready(function() {
        _goodsTable = $('#goodsTable').bootstrapTable({
            sidePagination:'server',//设置为服务器端分页
            url: '<%=root%>/goods/list.do',
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
                {field: 'opt',width: '15%', title: '选择', align: 'center',
                    formatter: function(value, row){
                        return '<input type="checkbox" value="'+row.id+'" name="goodsChoose" />';
                    }},
                {field: 'name',width: '15%', title: '名称', align: 'center'},
                {field: 'cateName', width: '10%', title: '类别', align: 'center'},
                {field: 'salePrice', width: '10%', title: '售卖价格', align: 'center'},
                {field: 'originalPrice', width: '10%', title: '成本价格', align: 'center'},
                {field: 'remark',width: '20%', title: '描述', align: 'center'},
                {field: 'num',width: '15%', title: '数量', align: 'center',
                    formatter: function(value, row){
                        return '<input type="text" id="'+row.id+'" />';
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
        _goodsTable.bootstrapTable("refresh");
    }

    function addOrder() {
        var json = [];
        $('input[name="goodsChoose"]:checked').each(function(){
            var j = {};
            j.key = $(this).val();
            j.value = $("#"+$(this).val()+"").val();
            if (!j.value) {
                toastr.error("请输入数量");
                die;
            }
            json.push(j);
        });
        var a = JSON.stringify(json);
        $.ajax({
            url: "<%=root%>order/addOrder.do",
            type: "post",
            data: {order:a,
                type:11},
            async: false,
            dataType: "json",
            success:function(data) {
                if (data.msg) {
                    toastr.error(data.msg);
                } else {
                    toastr.info("添加成功");
                }
            }
        });
        window.location.href = "<%=root%>/order/sale.do";
    }
</script>
</html>