<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Authors</title>

</head>
<body>
<h1>Authors list</h1>
<h2>&nbsp;&nbsp;&nbsp;
    <br> <br>

    <h2>Find author</h2>
    <form action="/author/find" method="post">
        <tr>
            <td>
                <input type="text" name="name" size="25"
                       value="<c:out value='${author.name}' />"
                />
                <input type="submit" value="Search"/>
            </td>
        </tr>
    </form>
</h2>

</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Authors</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        <c:forEach var="author" items="${listAuthors}">
            <tr>
                <td><c:out value="${author.id}" /></td>
                <td><c:out value="${author.name}" /></td>
                <td>
                    <a href="/book/listBook?name=<c:out value='${author.name}' />">All books</a>

                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>