<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>History Title</title>
</head>
<body>
<header class="content__title">
    <h1>DATA TABLES</h1>

    <div class="actions">
        <a href="#" class="actions__item zmdi zmdi-trending-up"></a>
        <a href="#" class="actions__item zmdi zmdi-check-all"></a>

        <div class="dropdown actions__item">
            <i data-toggle="dropdown" class="zmdi zmdi-more-vert"></i>
            <div class="dropdown-menu dropdown-menu-right">
                <a href="#" class="dropdown-item">Refresh</a>
                <a href="#" class="dropdown-item">Manage Widgets</a>
                <a href="#" class="dropdown-item">Settings</a>
            </div>
        </div>
    </div>
</header>

<div class="card">
    <div class="card-body">
        <h4 class="card-title">Move item history</h4>
        <div class="text-right">
            <input type="date" id="fromHistory" name="fromHistory">
            <input type="date" id="toHistory" name="toHistory">
            <button id="exportExcel" type="button" class="btn btn-warning">Download excel</button>
        </div>
        <div class="table-responsive">
            <table id="data-table-item" class="table">
                <thead>
                <tr>
                    <th>Classification</th>
                    <th>Item ID</th>
                    <th>Item name</th>
                    <th>Unit</th>
                    <th>Color</th>
                    <th>Remark</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<script>
    var table;
    $(document).ready(function () {
        table = $('#data-table-item').DataTable({
            'ajax': {
                'url': '/api/items',
                'method': 'GET',
                'dataSrc': ''
            },
            'columns': [
                {
                    data: 'classification',
                    'render': function (data, type, full, meta) {
                        return (data) ? data.value : null;
                    }
                },
                {data: 'id'},
                {data: 'name'},
                {
                    data: 'unit',
                    'render': function (data, type, full, meta) {
                        return (data) ? data.value : null;
                    }
                },
                {data: 'color'},
                {data: 'remark'}
            ],
            'order': [[0, 'asc']]
        });
    });

    $('#exportExcel').click(function (e) {
        var fromVal = $('#fromHistory').val();
        var toVal = $('#toHistory').val();
        if(fromVal && toVal){
            if(new Date(fromVal) <= new Date(toVal)){
                console.log(123);
            }
        }
    });
</script>
</body>
</html>