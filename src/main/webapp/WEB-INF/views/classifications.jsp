<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Classification Title</title>
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
        <h4 class="card-title">Classification</h4>
        <button id="btnAddClassification" type="button" class="btn btn-success"
                data-toggle="modal" data-target="#modal-add">Add</button>

        <%-- modal add --%>
        <div class="modal fade" id="modal-add" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title pull-left">Add classification</h5>
                    </div>
                    <div class="modal-body">
                        <form action="" id="formSubmitAddClassification">
                            <div class="form-group">
                                <input id="inputAddClassification" name="value" type="text" class="form-control" placeholder="Input value">
                                <i class="form-group__bar"></i>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="btnAddClassificationSave" type="button" class="btn btn-link" data-dismiss="modal">Save changes</button>
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
                        <h5 class="modal-title pull-left">Edit classification</h5>
                    </div>
                    <div class="modal-body">
                        <form action="" id="formSubmitEditClassification">
                            <div class="form-group">
                                <input type="hidden" id="idHiddenEditClassification" name="id"/>
                                <input id="inputEditClassification" name="value" type="text" class="form-control" placeholder="Input value">
                                <i class="form-group__bar"></i>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="btnEditClassificationSave" type="button" class="btn btn-link" data-dismiss="modal">Save changes</button>
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
                        <h5 class="modal-title pull-left">Delete classification</h5>
                    </div>
                    <div class="modal-body">
                            <input type="hidden" id="idHiddenDeleteClassification" name="id"/>
                            Are you sure you want to delete this item?
                    </div>
                    <div class="modal-footer">
                        <button id="btnDeleteClassificationSave" type="button" class="btn btn-link" data-dismiss="modal">Save changes</button>
                        <button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="table-responsive">
            <table id="data-table-classification" class="table">
                <thead>
                <tr>
                    <th>Value</th>
                    <th>Action</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<script src="/mylib/js/call-ajax.js"></script>

<script>
    var table;
    $(document).ready(function (){
        table = $('#data-table-classification').DataTable({
            'ajax': {
                'url': '/api/classifications',
                'method': 'GET',
                'dataSrc': ''
            },
            'columns': [
                { data: 'value' },
                {
                    'render': function (data, type, full, meta){
                        return '<button type="button"  onclick="clickBtnEditOrDelete(' + full.id + ',\'' + full.value + '\', \'edit\')" class="btn btn-primary editClassification" data-toggle="modal" data-target="#modal-edit">Edit</button> ' +
                            '<button type="button" onclick="clickBtnEditOrDelete(' + full.id + ',\'' + full.value + '\', \'delete\')" class="btn btn-danger deleteClassification" data-toggle="modal" data-target="#modal-delete">Delete</button>';
                    }
                }
            ],
            // order: [[0, 'asc']],
            // pageLength: 100,
            // lengthChange: false,
            dom: 'Bfrtip',
            // orderCellsTop: true,
            // destroy: true,
            // retrieve: true,
            // processing: true,
            // serverSide: true,
            filter: false,
            bSort: false,
            // //info: false,
            // autoWidth: false,
            // //bPaginate: false,
        });
    });

    // add
    $('#btnAddClassification').click(function (e) {
        $('#formSubmitAddClassification').trigger('reset');
    });
    handleClick("#formSubmitAddClassification", "#btnAddClassificationSave", "/api/classifications", "post",
        function (result) {
            if(result.status){
                table.ajax.reload();
                swal("Add classification!", "You have successfully added a classification!", "success");
            }else{
                swal(result.data);
            }
        });

    // edit or delete
    function clickBtnEditOrDelete(id, value, type){
        if(type=='edit'){
            $('#inputEditClassification').val(value);
            $('#idHiddenEditClassification').val(id);
        }else{
             $('#idHiddenDeleteClassification').val(id);
        }
    }

    handleClick("#formSubmitEditClassification", "#btnEditClassificationSave",
        "/api/classifications", "put", function (result) {
            if(result.status){
                table.ajax.reload();
                swal("Edit classification!", "You have successfully edited a classification!", "success");
            }else{
                swal(result.data);
            }
        });

    $('#btnDeleteClassificationSave').click(function (e) {
        e.preventDefault();
        var data = $('#idHiddenDeleteClassification').val();
        callDB("/api/classifications",  "delete", data,  function (result) {
            if(result.status){
                table.ajax.reload();
                swal("Delete classification!", "You have successfully deleted a classification!", "success");
            }else{
                swal(result.data);
            }
        });
    });
</script>
</body>
</html>
