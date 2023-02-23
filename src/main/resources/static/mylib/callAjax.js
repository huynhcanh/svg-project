function callDB(urlApi, type, objectData, itemName, urlSuccess, action) {
    $.ajax({
        url: urlApi,
        type: type,
        contentType: 'application/json',
        data: JSON.stringify(objectData),
        dataType: 'json',
        success: function (result) {
            swal(action + " " + itemName + "!", "You have successfully"+ action + "ed a " + itemName + "!", "success");
            setTimeout(() => window.location.href = urlSuccess,1000);
        },
        error: function (xhr) {
            var err = JSON.parse(xhr.responseText);
            swal(err.message);
        }
    });
}

function handleClick(idForm, idBtn, urlApi, type, itemName, urlSuccess, action){
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
        callDB(urlApi, type, data, itemName, urlSuccess, action);
    });
}