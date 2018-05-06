<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../base.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<%=root %>/resources/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <title>门店管理</title>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>日志</h5>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table-striped table-bordered table-hover" id="logTable">
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
    var _logTable;
    $(document).ready(function() {
        _logTable = $('#logTable').bootstrapTable({
            sidePagination:'server',//设置为服务器端分页
            url: '<%=root%>/operationLog/list.do',
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
                {field: 'userName',width: '20%', title: '操作人', align: 'center'},
                {field: 'descCode', width: '60%', title: '名称', align: 'center'},
                {field: 'createTime',width: '20%', title: '创建时间', align: 'center',
                    formatter : function(value) {
                        return $(this).dateFormat(value, 'yyyy-MM-dd HH:mm:ss');
                    }}
            ],
            toolbar: '#toolbar'
        });
    });
</script>
</html>