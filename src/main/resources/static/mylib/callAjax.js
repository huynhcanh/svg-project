function callDB(urlApi, type, data, cb) {
    $.ajax({
        url: urlApi,
        type: type,
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (result) {
            cb({
                status: true,
                data: result
            });
        },
        error: function (xhr) {
            cb({
                status: false,
                data: JSON.parse(xhr.responseText).message
            });
        }
    });
}

function handleClick(idForm, idBtn, urlApi, type, cb){
    $(idBtn).click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $(idForm).serializeArray();
        if(type == 'delete') {
            data = [formData[0].value];
        }else{
            $.each(formData, function (i, v) {
                data[""+v.name+""] = v.value;
            });
        }
        callDB(urlApi, type, data, cb);
    });
}