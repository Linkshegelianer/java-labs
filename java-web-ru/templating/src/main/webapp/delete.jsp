<!-- confirm user deletion -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language = "java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>delete</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
    <p>Вы действительно хотите удалить ${user.get("firstName")} ${user.get("lastName")}</p>
    <form action="/users/delete?id=${user.get("id")}" method="post">
        <button type="submit" class="btn btn-danger">Удалить</button>
    </form>
    </body>
</html>

