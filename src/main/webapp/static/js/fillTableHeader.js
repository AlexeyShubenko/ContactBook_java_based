/**
 * Created by Alexey on 09.09.2017.
 */
function addTableHead(table) {
    var header = table.insertRow(0);
    header.className = "head";
    var newCell = header.insertCell(0);
    newCell.innerHTML="Last name";
    var newCell = header.insertCell(1);
    newCell.innerHTML="First name";
    var newCell = header.insertCell(2);
    newCell.innerHTML="Middle name";
    var newCell = header.insertCell(3);
    newCell.innerHTML="Mobile phone number";
    var newCell = header.insertCell(4);
    newCell.innerHTML="Home phone number";
    var newCell = header.insertCell(5);
    newCell.innerHTML="Address";
    var newCell = header.insertCell(6);
    newCell.innerHTML="Email";
    var newCell = header.insertCell(7);
    newCell.innerHTML="Delete";
    var newCell = header.insertCell(8);
    newCell.innerHTML="Update";
}

//with spring translation
/*
 var header = table.insertRow(0);
 header.className = "head";
 var newCell = header.insertCell(0);
 newCell.innerHTML="<spring:message code='contact.params.firstname'/>";
 var newCell = header.insertCell(1);
 newCell.innerHTML="<spring:message code='contact.params.lastname'/>";
 var newCell = header.insertCell(2);
 newCell.innerHTML="<spring:message code='contact.params.middlename'/>";
 var newCell = header.insertCell(3);
 newCell.innerHTML="<spring:message code='contact.params.mobPhoneNumber'/>";
 var newCell = header.insertCell(4);
 newCell.innerHTML="<spring:message code='contact.params.homePhoneNumber'/>";
 var newCell = header.insertCell(5);
 newCell.innerHTML="<spring:message code='contact.params.address'/>";
 var newCell = header.insertCell(6);
 newCell.innerHTML="Email";
 var newCell = header.insertCell(7);
 newCell.innerHTML="<spring:message code='contact.params.delete'/>";
 var newCell = header.insertCell(8);
 newCell.innerHTML="<spring:message code='contact.params.update'/>";
* */