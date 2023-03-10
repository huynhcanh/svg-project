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
            <form id="frm-history">
                <input type="date" id="fromHistory" name="fromHistory">
                <input type="date" id="toHistory" name="toHistory">
                <button id="exportExcel" type="button" class="btn btn-warning">Download excel</button>
            </form>
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
            'ajax': {
                'url': '/api/histories',
                'method': 'GET',
                'dataSrc': ''
            },
            'columns': [
                {data: 'createdDate'},
                {data: 'createdBy'},
                {data: 'event'},
                {
                    data: 'item',
                    'render': function (data, type, full, meta) {
                        return (data) ? data.id : null;
                    }
                },
                {
                    data: 'item',
                    'render': function (data, type, full, meta) {
                        return (data) ? data.name : null;
                    }
                },
                {data: 'quantity'},
                {
                    data: null,
                    'render': function (data, type, full, meta) {
                        return data.fromLocation + ' <i class="fas fa-caret-right"></i> ' + data.toLocation;
                    }
                },
                {data: 'note'}
            ],
            'order': [[0, 'asc']],
            'filter': false,
            'bSort': false
        });
    });

    $('#exportExcel').click(function (e) {
        e.preventDefault();
        var fromVal = $('#fromHistory').val();
        var toVal = $('#toHistory').val();
        if(fromVal && toVal){
            if(new Date(fromVal) <= new Date(toVal)){
                var data = {};
                var formData = $('#frm-history').serializeArray();
                $.each(formData, function (i, v) {
                    data[""+v.name+""] = v.value;
                });
                console.log(data);
                callDB('/api/item-location-history', 'post', data, function (result) {
                    if(result.status){
                        console.log('thanh cong');
                    }else{
                        console.log('that bai');
                    }
                });
            }else{
                console.log('sai yeu cau');
            }
        }
    });
</script>
</body>
</html>