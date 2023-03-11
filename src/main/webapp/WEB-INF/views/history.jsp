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
            <table id="data-table-history" class="table">
                <thead>
                <tr>
                    <th>Time</th>
                    <th>Logged User</th>
                    <th>Event</th>
                    <th>Item ID</th>
                    <th>Item name</th>
                    <th>Qty</th>
                    <th>From <i class="fas fa-caret-right"></i> To</th>
                    <th>Note</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<script src="/mylib/js/call-ajax.js"></script>

<script>
    var table;
    $(document).ready(function () {
        table = $('#data-table-history').DataTable({
            processing: true,
            serverSide: true,
            ajax: {
                url: '/api/histories/filter',
                contentType: 'application/json',
                type: 'POST',
                data: function ( d ) {
                    d.fromHistory = $('#fromHistory').val();
                    d.toHistory = $('#toHistory').val();
                    return JSON.stringify( d );
                },
                dataSrc: function (json) {
                    // Xử lý kết quả tìm kiếm từ API trước khi trả về cho DataTable
                    return json;
                }
            },
            columns: [
                {data: 'createdDate'},
                {data: 'createdBy'},
                {data: 'event'},
                {
                    data: 'item',
                    render: function (data, type, full, meta) {
                        return (data) ? data.id : null;
                    }
                },
                {
                    data: 'item',
                    render: function (data, type, full, meta) {
                        return (data) ? data.name : null;
                    }
                },
                {data: 'quantity'},
                {
                    data: null,
                    render: function (data, type, full, meta) {
                        return data.fromLocation + ' <i class="fas fa-caret-right"></i> ' + data.toLocation;
                    }
                },
                {data: 'note'}
            ],
            dom: 'Bfrtip',
            filter: false,
            bSort: false,
        });
    });

    $("#fromHistory").change(function() {
        var fromHistory = $(this).val();
        var toHistory = $('#toHistory').val();
        callDB('/api/histories/filter', 'post', {fromHistory, toHistory}, function (result) {
            if(result.status){
                table.clear();
                table.rows.add(result.data);
                table.draw();
            }else{
                console.log('that bai');
            }
        });
    });

    $("#toHistory").change(function() {
        var fromHistory = $('#fromHistory').val();
        var toHistory = $(this).val();
        callDB('/api/histories/filter', 'post', {fromHistory, toHistory}, function (result) {
            if(result.status){
                table.clear();
                table.rows.add(result.data);
                table.draw();
            }else{
                console.log('that bai');
            }
        });
    });

    $('#exportExcel').click(function (e) {
        e.preventDefault();

        var fromHistory = $('#fromHistory').val();
        var toHistory = $('#toHistory').val();
        if(fromVal && toVal){
            callDB('/api/histories/export-excel', 'post', {fromHistory, toHistory}, function (result) {
                if(result.status){
                    console.log('thanh cong');
                }else{
                    console.log('that bai');
                }
            });
        }
    });
</script>
</body>
</html>