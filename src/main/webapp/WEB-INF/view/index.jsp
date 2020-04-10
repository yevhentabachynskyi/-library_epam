<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LIBRARY</title>

</head>
<body>
<h1>Readers Management</h1>
<h2>
    <a href="new">Add New Book</a>
    &nbsp;&nbsp;&nbsp;
    <a href="list">List All Books</a>

</h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Readers</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Phone</th>
        </tr>
        <c:forEach var="reader" items="${listReader}">
            <tr>
                <td><c:out value="${reader.id}" /></td>
                <td><c:out value="${reader.name}" /></td>
                <td><c:out value="${reader.address}" /></td>
                <td><c:out value="${reader.phone}" /></td>
                <td>
                    <a href="edit?id=<c:out value='${reader.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=<c:out value='${reader.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>