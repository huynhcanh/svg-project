function callDB(urlApi, type, data, cb) {
    $.ajax({
        url: urlApi,
        type: type,
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (result) {
            if(typeof cb === 'function'){
                cb({
                    status: true,
                    data: result
                });
            }
        },
        error: function (xhr) {
            if(typeof cb === 'function'){
                cb({
                    status: false,
                    data: JSON.parse(xhr.responseText).message
                });
            }
        }
    });
}

function handleClick(idForm, idBtn, urlApi, type, cb){
    $(idBtn).click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $(idForm).serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
        callDB(urlApi, type, data, cb);
    });
}

function loadSelect(idSelect, api, optionName, codeSelected){
    callDB(api, "get", null, function (result) {
        var array = result.data;
        $(idSelect).empty().append('<option value="">---- ' + optionName + ' ----</option>');
        if(array){
            for(var item of array){
                if(typeof item === 'object'){
                    if(codeSelected && codeSelected === item.code){
                        $(idSelect).append('<option selected value="' + item.code + '">'
                            + item.value + '</option>');
                    }else{
                        $(idSelect).append('<option value="' + item.code + '">'
                            + item.value + '</option>');
                    }
                }else{
                    if(codeSelected){
                        $(idSelect).append('<option selected value="' + item + '">'
                            + item + '</option>');
                    }else{
                        $(idSelect).append('<option value="' + item + '">'
                            + item + '</option>');
                    }
                }
            }
        }
        // custom template: set color for option element
        $(idSelect).find('option').css('color', 'black');
    });
}