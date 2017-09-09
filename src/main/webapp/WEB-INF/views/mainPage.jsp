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
		<script src="./static/js/searchContacts.js"></script>
		<script src="./static/js/fillTableHeader.js"></script>
		<script src="./static/js/windowLoadJsFile.js"></script>

</head>
<body>

		<div class="divTop">
			<a href="/logOut" class="logOutRef">Log out</a>
			<div class="divRight">login: ${userDto.login}</div>
			<a class="addContact" href="./addContact"><spring:message code="button.value.addcontact"/></a>
		</div>

		<div class="search">
			<div>
				<input type="radio" id="byName" name="search" value="byName" checked="checked"> by name </br>
				<input type="radio" id="byNumber" name="search" value="byNumber"> by number </br>
			</div>
			<input type="text" id="searchField" onkeyup="searchByParameter()">
		</div>

		<h1 id="NoteCentr"><spring:message code="title.showcontacts"/></h1>

		<div id="myTable">
		</div>

</body>
</html>
