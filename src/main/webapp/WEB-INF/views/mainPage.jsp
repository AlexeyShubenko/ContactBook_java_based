<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>

	<link href="./static/css/myfile.css" rel="stylesheet" />

		<title>All contacts</title>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="./static/js/tableFill.js"></script>
		<script src="./static/js/allContacts.js"></script>
		<script>
            var contacts = [];

            window.onload=function () {
                getAllContacts();
            }

            function addTableHead(table) {
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
            }

			function searchByParameter() {
				var searchBy = $("input:checked").val();
				var parameter = $("input#search").val();
                clearContactTable("myTable");
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
                        console.log(data);
                        showAllContacts(data);
                    }
                });
            }
			
			
            function clearContactTable(elementId) {
                document.getElementById(elementId).innerHTML = "";
            }

	</script>

</head>
<body>

		<div class="divTop">
			<a href="/logOut" class="logOutRef">Log out</a>
			<div class="divRight">login: ${userDto.loginName}</div>
			<a class="addContact" href="./addContact"><spring:message code="button.value.addcontact"/></a>
		</div>

		<div class="search">
			<div>
				<input type="radio" id="byName" name="search" value="byName" checked="checked"> by name </br>
				<input type="radio" id="byNumber" name="search" value="byNumber"> by number </br>
			</div>
			<%--<input type="button" value="By number" id="byNumber" onclick="getContactsByNumber()"/>--%>
			<input type="text" id="search" onkeyup="searchByParameter()">
		</div>

		<h1 id="NoteCentr"><spring:message code="title.showcontacts"/></h1>

		<div id="myTable"></div>

</body>
</html>
