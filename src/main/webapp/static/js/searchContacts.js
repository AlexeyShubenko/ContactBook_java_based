/**
 * Created by Alexey on 10.08.2017.
 */
function searchByParameter() {
    // var radioValues = document.getElementsByName("search");
    // var searchBy;
    // for (var i = 0; i < radioValues.length; i++) {
    //     if (radioValues[i].type === 'radio' && radioValues[i].checked) {
    //         searchBy = radioValues[i].value;
    //     }
    // }

    var searchBy = $("input:checked").val();
    var parameter = document.getElementById("searchField").value;
        //$("input#search").val();

    var table = document.getElementById("contactTable");
    deletePreviousContacts(table);
    if(parameter!=""){
        getContactsByParameter(searchBy, parameter);
    }else{
        showAllContacts(contacts);
    }
}


function getContactsByParameter(searchBy, parameter) {
    var data = {};
    data["searchBy"] = searchBy;
    data["parameter"] = parameter;
    $.ajax({
        type: 'POST',
        url: 'getContactsByName',
        contentType: 'application/json',
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            showAllContacts(data);
        }
    });
}