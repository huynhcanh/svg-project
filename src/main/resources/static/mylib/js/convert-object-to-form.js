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
    selects.forEach(function (select, index) {
        // load list items and recss
        if(select.selected !== null){
            loadSelect(select.selectId, select.api, select.nameSelect,
                select.selected.code);
        }else{
            loadSelect(select.selectId, select.api, select.nameSelect);
        }
    });
}