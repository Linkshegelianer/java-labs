<!-- Show one particular user + link to delete the user -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<!-- // template from unit -->
    <head>
        <meta charset="UTF-8">
        <title>show</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
	<body>
	    <table>
	        <tr>
	        <th>id</th>
            <th>first name</th>
            <th>last name</th>
            <th>email</th>
            </tr>

            <tr>
            <td>${user.get("id")}</td>
            <td>${user.get("firstName")}</td>
            <td>${user.get("lastName")}</td>
            <td>${user.get("email")}</td>
            </tr>
	    </table>
        <a href='/users/delete?id=${user.get("id")}'><button type="submit">Удалить пользователя</button></a>
	</body>
</html>
