<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Location Title</title>
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
        <h4 class="card-title">Location</h4>
        <button id="btnAddLocation" type="button" class="btn btn-success"
                data-toggle="modal" data-target="#modal-add">Add</button>
        <%-- modal add --%>
        <div class="modal fade" id="modal-add" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title pull-left">Add location</h5>
                    </div>
                    <div class="modal-body">
                        <form action="" id="formSubmitAddLocation">
                            <div class="row">
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
                                <div class="col-sm-12">
                                    <div class="form-group">
                                        <label>Result: </label>
                                        <span id="resultTxtAddLocation"></span>
                                        <i class="form-group__bar"></i>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="btnAddLocationSave" type="button" class="btn btn-link" data-dismiss="modal">Save changes</button>
                        <button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="table-responsive">
            <form id="frm-location" action="">
                <table id="data-table-location" class="table">
                    <thead>
                    <tr>
                        <th>Warehouse</th>
                        <th>Rack</th>
                        <th>Tray</th>
                        <th>QR</th>
                        <th>
                            <input type="checkbox" name="select_all" value="1" id="example-select-all">
                            Excel Delete</th>
                    </tr>
                    </thead>
                </table>
                <button type="submit" class="btn btn-primary">submit</button>
            </form>
        </div>
    </div>
</div>
<script>
    var table;
    $(document).ready(function (){
        table = $('#data-table-location').DataTable({
            'ajax': {
                'url': '/api/locations',
                'method': 'GET',
                'dataSrc': ''
            },
            'columns': [
                { data: 'warehouse' },
                { data: 'rack' },
                { data: 'tray' },
                { data: 'id' },
                {
                    data: 'id',
                    'render': function (data, type, full, meta){
                        return '<input type="checkbox" name="ids" value="'+data+'">';
                    }
                }
            ],
            'order': [[0, 'asc']]
            // "drawCallback": function( settings ) { // done datatable
            //     alert( 'DataTables has redrawn the table' );
            // },
            // "rowCallback": function( row, data ) { // tung dong
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
        $('#data-table-location tbody').on('change', 'input[type="checkbox"]', function(){
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
        // Handle form submission event
        $('#frm-location').on('submit', function(e){
            e.preventDefault();
            var ids = [];
            // Iterate over all checkboxes in the table
            table.$('input[type="checkbox"]').each(function(e, v){
                if(v.checked){
                    ids.push(v.value);
                }
            });
            callDB('/api/locations', 'delete', ids, 'Location', '/locations', 'Delete');
        });

    });


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

    // add
    $('#btnAddLocation').click(function (e) {
        var warehouseTxtAddLocation =  $('#warehouseTxtAddLocation');
        var rackTxtAddLocation =  $('#rackTxtAddLocation');
        var trayTxtAddLocation =  $('#trayTxtAddLocation');
        var resultTxtAddLocation = $('#resultTxtAddLocation');
        var elements = [warehouseTxtAddLocation, rackTxtAddLocation, trayTxtAddLocation];
        elements.forEach(function (v, i) {
            v.val('');
            keyUpEvent(elements, i, resultTxtAddLocation);
        });
        resultTxtAddLocation.text('');
    });
    handleClick("#formSubmitAddLocation", "#btnAddLocationSave", "/api/locations", "post",
        "location", "/locations", "Add");




    // // edit or delete
    // function clickBtnEditOrDelete(id, value, type){
    //     if(type=='edit'){
    //         $('#inputEditClassification').val(value);
    //         $('#idHiddenEditClassification').val(id);
    //         handleClick("#formSubmitEditClassification", "#btnEditClassificationSave",
    //             "/api/classifications", "put", "classification", "/classifications", "Edit");
    //     }else{
    //          $('#idHiddenDeleteClassification').val(id);
    //          handleClick("#formSubmitDeleteClassification", "#btnDeleteClassificationSave", "/api/classifications", "delete",
    //              "classification", "/classifications", "Delete");
    //     }
    // }
</script>
</body>
</html>
