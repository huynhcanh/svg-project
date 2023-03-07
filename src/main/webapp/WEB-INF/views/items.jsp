<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Item Title</title>
</head>
<body>
<link rel="stylesheet" href="/mylib/css/show-img-modal.css">
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
        <h4 class="card-title">Item</h4>
        <button id="btnAddItem" type="button" class="btn btn-success"
                data-toggle="modal" data-target="#modal-add">Add by web</button>
        <br> <br>
        <div>
            <label for="">Add by excel</label> <br>
            <input id="inputAddItemByExcel" type="file" accept=".xlsx, .xls, .csv" class="btn btn-success" />
            <button id="btnAddItemByExcel" type="button" class="btn btn-success">Add by excel</button>
        </div>

        <%-- modal add --%>
        <div class="modal fade" id="modal-add" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title pull-left">Add item</h5>
                    </div>
                    <div class="modal-body">
                        <form action="" id="formSubmitAddItem">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h3 class="card-body__title">Classification</h3>
                                    <div class="form-group">
                                        <div class="select">
                                            <select name="classificationCode" id="classificationSelectAddItem" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <h3 class="card-body__title">Unit</h3>
                                    <div class="form-group">
                                        <div class="select">
                                            <select name="unitCode" id="unitSelectAddItem" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <input name="name" type="text" class="form-control" placeholder="Name">
                                        <i class="form-group__bar"></i>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <input name="color" type="text" class="form-control" placeholder="Color">
                                        <i class="form-group__bar"></i>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <input name="remark" type="text" class="form-control" placeholder="Remark">
                                        <i class="form-group__bar"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-12">
                                    <h3 class="card-body__title">Location:</h3>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <div class="select">
                                            <select name="warehouse" id="warehouseSelectAddItem" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <div class="select">
                                            <select name="rack" id="rackSelectAddItem" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <div class="select">
                                            <select name="tray" id="traySelectAddItem" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <input name="quantity" type="number" class="form-control" placeholder="Quantity">
                                        <i class="form-group__bar"></i>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="btnAddItemSave" type="button" class="btn btn-link" data-dismiss="modal">Save changes</button>
                        <button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <%-- modal delete --%>
        <div class="modal fade" id="modal-delete" tabindex="-1">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title pull-left">Delete location</h5>
                    </div>
                    <div class="modal-body">
                        <form action="" id="formSubmitDeleteClassification">
                            Are you sure you want to delete this item?
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="btnDeleteItemSave" type="button" class="btn btn-link" data-dismiss="modal">Save changes</button>
                        <button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <%-- modal edit --%>
        <div class="modal fade" id="modal-edit" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title pull-left">Edit item</h5>
                    </div>
                    <div class="modal-body">
                        <form action="" id="formSubmitEditItem">
                            <input type="hidden" id="id" name="id"/>
                            <div class="row">
                                <div class="col-sm-6">
                                    <h3 class="card-body__title">Classification</h3>
                                    <div class="form-group">
                                        <div class="select">
                                            <select id="classificationSelectEditItem" name="classificationCode" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <h3 class="card-body__title">Unit</h3>
                                    <div class="form-group">
                                        <div class="select">
                                            <select id="unitSelectEditItem" name="unitCode" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <input name="name" type="text" class="form-control" placeholder="Name">
                                        <i class="form-group__bar"></i>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <input name="color" type="text" class="form-control" placeholder="Color">
                                        <i class="form-group__bar"></i>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <input name="remark" type="text" class="form-control" placeholder="Remark">
                                        <i class="form-group__bar"></i>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="btnEditItemSave" type="button" class="btn btn-link" data-dismiss="modal">Save changes</button>
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
                        <th>Classification</th>
                        <th>Item ID</th>
                        <th>Item name</th>
                        <th>Unit</th>
                        <th>Color</th>
                        <th>Remark</th>
                        <th>QR code</th>
                        <th><input type="checkbox" name="select_all" value="1" id="example-select-all"></th>
                        <th>
                            <button id="exportExcel" style="margin-left: 20px"type="button" class="btn btn-success">Excel</button>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modal-delete">Delete</button>
                        </th>
                    </tr>
                    </thead>
                </table>
            </form>
        </div>
    </div>
</div>

<!-- The Modal -->
<div id="my-modal" class="my-modal">
    <div class="my-modal-content">
        <span class="close-my-modal">&times;</span>
        <img id="img-my-modal">
    </div>
</div>

<script src="/mylib/js/call-ajax.js"></script>
<script src="/mylib/js/show-img-modal.js"></script>
<script src="/mylib/js/convert-object-to-form.js"></script>
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
                    data: 'classification',
                    'render': function (data, type, full, meta) {
                        return (data) ? data.value: null;
                    }
                },
                { data: 'id' },
                { data: 'name' },
                {
                    data: 'unit',
                    'render': function (data, type, full, meta) {
                        return (data) ? data.value: null;
                    }
                },
                { data: 'color' },
                { data: 'remark' },
                {
                  data: 'id',
                  'render': function (data, type, full, meta){
                      return '<img alt="qr image" class="item-img-qr" src="/api/generate-qr?id=' + data + '" width="50">'
                  }
                },
                {
                    data: 'id',
                    'render': function (data, type, full, meta){
                        return '<input type="checkbox" name="ids" value="'+data+'">';
                    }
                },
                {
                    data: 'id',
                    'render': function (data, type, full, meta){
                        return '<button value="'+data+'" type="button" class="btnEditItem btn btn-info"' +
                            'data-toggle="modal" data-target="#modal-edit">Edit</button>';
                    }
                }
            ],
            'order': [[0, 'asc']],
            "drawCallback": function( settings ) { // active when draw done datatable
                // show Qr image
                $('.item-img-qr').click(function (e) {
                    handleShowFullImg(this, "my-modal", "img-my-modal", "close-my-modal");
                });
                // edit item
                $('.btnEditItem').click(function (e) {
                    e.preventDefault();
                    // show info
                    callDB('/api/item?id=' + $(this).val(), 'get', null, function (result) {
                        if(result.status){
                            convertObjectToForm('#formSubmitEditItem', result.data,
                                {
                                    selectId: '#classificationSelectEditItem',
                                    api: '/api/classifications',
                                    nameSelect: 'Classification',
                                    selected: result.data.classification
                                },
                                {
                                    selectId: '#unitSelectEditItem',
                                    api: '/api/units',
                                    nameSelect: 'Unit',
                                    selected: result.data.unit
                                }
                            );
                        }else{
                            swal(result.data);
                        }
                    });
                });
            }
            // "rowCallback": function( row, data ) { // active when draw done datatable
            //     console.log(row,data);
            // }
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

    // open add modal
    $('#btnAddItem').click(function (e) {
        // reset all input in form
        $('#formSubmitAddItem').trigger('reset');

        // load list classification and recss
        loadSelect('#classificationSelectAddItem','/api/classifications', 'Classification');

        // load list unit
        loadSelect('#unitSelectAddItem','/api/units', 'Unit');

        // load list warehouse
        loadSelect('#warehouseSelectAddItem','/api/locations/warehouses', 'Warehouse');
    });

    $('#warehouseSelectAddItem').change(function() {
        var warehouse = $(this).val();
        if(warehouse){
            loadSelect('#rackSelectAddItem','/api/locations/racks/warehouse?warehouse='+ warehouse , 'Rack');
        }
        else {
            $('#rackSelectAddItem').empty();
        }
        $('#traySelectAddItem').empty();
    });

    $('#rackSelectAddItem').change(function() {
        var warehouse = $('#warehouseSelectAddItem').val();
        var rack = $(this).val();
        if(rack){
            loadSelect('#traySelectAddItem','/api/locations/trays/' +
                'warehouse-rack?warehouse=' + warehouse + '&rack=' + rack, 'Tray');
        }
        else $('#traySelectAddItem').empty();
    });

    handleClick("#formSubmitAddItem", "#btnAddItemSave", "/api/item-location", "post",
        function (result) {
            if(result.status){
                table.ajax.reload();
                swal("Add Item!", "You have successfully added a item!", "success");
            }else{
                swal(result.data);
            }
        });

    // delete
    $('#btnDeleteItemSave').click(function (e) {
        // Handle form submission event
        e.preventDefault();
        var ids = [];
        // Iterate over all checkboxes in the table
        table.$('input[type="checkbox"]').each(function(e, v){
            if(v.checked){
                ids.push(v.value);
            }
        });
        if(ids.length != 0){
            callDB('/api/items', 'delete', ids, function (result) {
                if(result.status){
                    table.ajax.reload();
                    swal("Delete item!", "You have successfully deleted a item!", "success");
                }else{
                    swal(result.data);
                }
            });
        }else{
            swal('You have not selected the items');
        }
    });

    // edit
    handleClick("#formSubmitEditItem", "#btnEditItemSave",
        "/api/items", "put", function (result) {
            if(result.status){
                table.ajax.reload();
                swal("Edit item!", "You have successfully edited a item!", "success");
            }else{
                swal(result.data);
            }
        });

    // export excel
    $('#exportExcel').click(function (e) {
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
        if(ids.length != 0){
            callDB('/api/items/export-excel?ids=' + apiParams, 'get', null);
        }else{
            swal('You have not selected the items');
        }
    });

    // Add by excel
    $('#btnAddItemByExcel').click(function() {
        var file = $('#inputAddItemByExcel').prop('files')[0];
        var formData = new FormData();
        formData.append('file', file);

        $.ajax({
            url: '/api/items/import-excel',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function(data) {
                table.ajax.reload();
                swal("Add items!", "You have successfully items!", "success");
            },
            error: function(xhr, status, error) {
                swal(xhr.responseText);
            }
        });
    });
</script>
</body>
</html>