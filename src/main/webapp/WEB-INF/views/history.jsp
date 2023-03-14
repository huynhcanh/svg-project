<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>History Title</title>
</head>
<body>

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
    var historyRequest = {
        fromHistory: $('#fromHistory').val(),
        toHistory: $('#toHistory').val()
    }

    var table;
    $(document).ready(function () {
        table = $('#data-table-history').DataTable({
            ajax: {
                url: '/api/histories/filter',
                contentType: 'application/json',
                dataType: 'json',
                method: 'POST',
                data: function (d) {
                    return JSON.stringify(historyRequest);
                },
                dataSrc: function (json) {
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
        historyRequest.fromHistory = $(this).val();
        table.ajax.reload();
    });

    $("#toHistory").change(function() {
        historyRequest.toHistory = $(this).val();
        table.ajax.reload();
    });

    $('#exportExcel').click(function (e) {
        e.preventDefault();

        var fromHistory = $('#fromHistory').val();
        var toHistory = $('#toHistory').val();
        callDB('/api/histories/export-excel', 'post', {fromHistory, toHistory}, function (result) {
            if(result.status){
                console.log('thanh cong');
            }else{
                console.log('that bai');
            }
        });
    });
</script>
</body>
</html>