function findValue(name, object) {
    if(object){
        for (const [key, value] of Object.entries(object)) {
            if(key === name) return value;
            else{
                if(typeof value === 'object'){
                    var rs = findValue(name, value);
                    if(rs) return rs;
                }
            }
        }
        return null;
    }
    return null;
}

function convertObjectToForm(idform, object, ...selects){
    $(idform).find('[name]').each(function (i, e) {
        $(e).val(findValue(e.name, object));
    });
    selects.forEach(function (value, index) {
        // load list items and recss
        if(value.selected !== null){
            loadSelect(value.selectId,value.api,
                value.selected.code);
        }else{
            loadSelect(value.selectId,value.api);
        }
    });
}