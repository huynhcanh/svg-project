<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Management Title</title>
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
        <h4 class="card-title">Managements</h4>

        <%-- modal move --%>
        <div class="modal fade" id="modal-move" tabindex="-1">
            <div class="modal-dialog modal-sm">
                <div class="modal-content" style="width: 1000px; margin-left: -320px;">
                    <div class="modal-header">
                        <h5 class="modal-title pull-left">MOVE ITEM</h5>
                    </div>
                    <div class="modal-body">
                        <form action="" id="formSubmitMoveItem">
                            <div class="row">
                                <div class="col-sm-12">
                                    <h3 class="card-body__title">Move to location: <span id="resultTxtAddLocation"></span></h3>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <input id="warehouseTxtAddLocation" name="warehouse" type="text" class="form-control" placeholder="Warehouse">
                                        <i class="form-group__bar"></i>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <input id="rackTxtAddLocation" name="rack" type="text" class="form-control" placeholder="Rack">
                                        <i class="form-group__bar"></i>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <input id="trayTxtAddLocation" name="tray" type="text" class="form-control" placeholder="Tray">
                                        <i class="form-group__bar"></i>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-sm mb-0">
                                <thead>
                                <tr>
                                    <th>Location</th>
                                    <th>Classification</th>
                                    <th>Item ID</th>
                                    <th>Item name</th>
                                    <th>Color</th>
                                    <th>Value/Qty</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="btnMoveItemSave" type="button" class="btn btn-link" data-dismiss="modal">Save changes</button>
                        <button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="table-responsive">
            <form id="frm-item" action="">
                <table id="data-table-item" class="table">
                    <thead>
                    <tr>
                        <th>Warehouse</th>
                        <th>Rack</th>
                        <th>Tray</th>
                        <th>Classification</th>
                        <th>ItemID</th>
                        <th>ItemName</th>
                        <th>Qty</th>
                        <th>Color</th>
                        <th>Move by</th>
                        <th><input type="checkbox" name="select_all" value="1" id="example-select-all"></th>
                        <th>
                            <button id="btnMoveItem" type="button" class="btn btn-success"
                                    data-toggle="modal" data-target="#modal-move">Move to</button>
                        </th>
                    </tr>
                    </thead>
                </table>
            </form>
        </div>
    </div>
</div>

<script src="/mylib/js/call-ajax.js"></script>
<script>
    var table;
    $(document).ready(function (){
        table = $('#data-table-item').DataTable({
            'ajax': {
                'url': '/api/items',
                'method': 'GET',
                'dataSrc': ''
            },
            'columns': [
                {
                    data: 'location',
                    'render': function (data, type, full, meta) {
                        return (data) ? data.warehouse: null;
                    }
                },
                {
                    data: 'location',
                    'render': function (data, type, full, meta) {
                        return (data) ? data.rack: null;
                    }
                },
                {
                    data: 'location',
                    'render': function (data, type, full, meta) {
                        return (data) ? data.tray: null;
                    }
                },
                {
                    data: 'classification',
                    'render': function (data, type, full, meta) {
                        return (data) ? data.value: null;
                    }
                },
                { data: 'id' },
                { data: 'name' },
                { data: 'quantity' },
                { data: 'color' },
                { data: 'modifiedBy' },
                {
                    data: 'id',
                    'render': function (data, type, full, meta){
                        return '<input type="checkbox" name="ids" value="'+data+'">';
                    }
                }
            ],
            'order': [[0, 'asc']],
            "drawCallback": function( settings ) { // active when draw done datatable

            }
        });

        // Handle click on "Select all" control
        $('#example-select-all').on('click', function(){
            // Get all rows with search applied
            var rows = table.rows({ 'search': 'applied' }).nodes();
            // Check/uncheck checkboxes for all rows in the table
            $('input[type="checkbox"]', rows).prop('checked', this.checked);
        });
        // Handle click on checkbox to set state of "Select all" control
        $('#data-table-item tbody').on('change', 'input[type="checkbox"]', function(){
            // If checkbox is not checked
            if(!this.checked){
                var el = $('#example-select-all').get(0);
                // If "Select all" control is checked and has 'indeterminate' property
                if(el && el.checked && ('indeterminate' in el)){
                    // Set visual state of "Select all" control
                    // as 'indeterminate'
                    el.indeterminate = true;
                }
            }
        });
    });

    // show result location
    function myResult(array){
        for(var x of array){
            if(!x.val()) return '';
        }
        return array.map(function (v) {
            return v.val();
        }).join('-');
    }

    function keyUpEvent(elements, index, resultElement){
        elements[index].keyup(function (e){
            resultElement.text(myResult(elements));
        });
    }

    // Click move button
    $('#btnMoveItem').click(function (e) {
        // reset all input in form
        $('#formSubmitMoveItem').trigger('reset');
        // handle location
        var warehouseTxtAddLocation =  $('#warehouseTxtAddLocation');
        var rackTxtAddLocation =  $('#rackTxtAddLocation');
        var trayTxtAddLocation =  $('#trayTxtAddLocation');
        var resultTxtAddLocation = $('#resultTxtAddLocation');
        var elements = [warehouseTxtAddLocation, rackTxtAddLocation, trayTxtAddLocation];
        elements.forEach(function (v, i) {
            keyUpEvent(elements, i, resultTxtAddLocation);
        });
        resultTxtAddLocation.text('');

        // Handle form submission event
        e.preventDefault();
        var ids = [];
        // Iterate over all checkboxes in the table
        table.$('input[type="checkbox"]').each(function(e, v){
            if(v.checked){
                ids.push(v.value);
            }
        });

        var apiParams = ids.join(',');
        callDB('/api/items-by-ids?ids=' + apiParams, 'get', null, function (result) {
            if(result.status){
                var tbodyE = $('#formSubmitMoveItem').find('tbody');
                tbodyE.empty();
                result.data.forEach(function (e) {
                    tbodyE.append('<tr>');
                    tbodyE.append('<td>' + e.location.warehouse + '-' + e.location.rack +
                        '-' + e.location.tray + '</td>');
                    tbodyE.append('<td>' + e.classification.value + '</td>');
                    tbodyE.append('<td>' + e.id + '</td>');
                    tbodyE.append('<td>' + e.name + '</td>');
                    tbodyE.append('<td>' + e.color + '</td>');
                    tbodyE.append('<td>' +
                        '<input type="hidden" name="id" value="' + e.id + '"/>' +
                        '<input name="quantity" class="w-25" type="text"> / <span class="text-red">' +
                        e.quantity + '</span></td>');
                    tbodyE.append('</tr>');
                });
            }else{
                swal(result.data);
            }
        });
    });

    $('#btnMoveItemSave').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmitMoveItem').serializeArray();
        $.each(formData, function (i, v) {
            // if(v.name) chua ton tai thi data[""+v.name+""] = v.value;
            // else { push vao v.name da ton tai}
            data[""+v.name+""] = v.value;
        });
        console.log(formData);
    })
    // handleClick("#formSubmitMoveItem", "#btnMoveItemSave", "/api/items-by-ids", "post",
    //     function (result) {
    //         if(result.status){
    //             table.ajax.reload();
    //             swal("Add Item!", "You have successfully added a item!", "success");
    //         }else{
    //             swal(result.data);
    //         }
    //     });
</script>
</body>
</html>