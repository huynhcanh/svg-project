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
        <div class="table-responsive">
            <table id="data-table" class="table">
                <thead>
                <tr>
                    <th>Value</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${classifications}">
                    <tr>
                        <td>${item.value}</td>
                        <td>
                            <button onclick="clickBtnEditOrDelete(${item.id}, '${item.value}', 'edit')" type="button" class="btn btn-primary"
                                    data-toggle="modal" data-target="#modal-edit">Edit</button>
                            <button onclick="clickBtnEditOrDelete(${item.id}, '${item.value}', 'delete')" type="button" class="btn btn-danger"
                                    data-toggle="modal" data-target="#modal-delete">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
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
                                <form action="" id="formSubmitDeleteClassification">
                                    <input type="hidden" id="idHiddenDeleteClassification" name="id"/>
                                    Are you sure you want to delete this item?
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button id="btnDeleteClassificationSave" type="button" class="btn btn-link" data-dismiss="modal">Save changes</button>
                                <button type="button" class="btn btn-link" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    // add
    $('#btnAddClassification').click(function (e) {
        $('#inputAddClassification').val('');
    });
    handleClick("#formSubmitAddClassification", "#btnAddClassificationSave", "/api/classifications", "post",
        "classification", "/classifications", "Add");

    // edit or delete
    function clickBtnEditOrDelete(id, value, type){
        if(type=='edit'){
            $('#inputEditClassification').val(value);
            $('#idHiddenEditClassification').val(id);
            handleClick("#formSubmitEditClassification", "#btnEditClassificationSave",
                "/api/classifications", "put", "classification", "/classifications", "Edit");
        }else{
             $('#idHiddenDeleteClassification').val(id);
             handleClick("#formSubmitDeleteClassification", "#btnDeleteClassificationSave", "/api/classifications", "delete",
                 "classification", "/classifications", "Delete");
        }
    }
</script>
</body>
</html>
