/**
 * Created by Alexey on 10.08.2017.
 */
function getAllContacts() {
    $.ajax({
        type: 'GET',
        url: 'getAllContacts',
        contentType: 'application/json',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var contact = {};
                contact.contact_id      = data[i].contact_id;
                contact.firstName       = data[i].firstName;
                contact.lastName        = data[i].lastName;
                contact.middleName      = data[i].middleName;
                contact.mobPhoneNumber  = data[i].mobPhoneNumber;
                contact.homePhoneNumber = data[i].homePhoneNumber;
                contact.address         = data[i].address;
                contact.email           = data[i].email;
                contacts.push(contact);
            }
            showAllContacts(contacts);
        }
    });
};


function showAllContacts(contacts){
    var table = document.getElementById("contactTable");
    if(table==null){
        var table = document.createElement("table");
        table.id="contactTable";
        table.className="myTableClass";
        addTableHead(table);
    }
    deletePreviousContacts(table,contacts.length);

    for (var i = 0; i < contacts.length; i++) {
        fillTable(table,i,contacts[i]);
    }
    var myDiv = document.getElementById("myTable");
    myDiv.appendChild(table);
}


function deletePreviousContacts(table) {
    for (var i = 1; i < table.rows.length; i++) {
        table.deleteRow(i);
    }
}