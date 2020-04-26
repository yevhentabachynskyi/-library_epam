<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Readers</title>

</head>
<body>
<h1>Readers Management</h1>
<h2>
    <a href="/reader/new">Add New Reader</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/reader/list">List All Readers</a>
    <br> <br>

    <h2>Find reader</h2>
    <form action="/reader/find" method="post">
        <tr>
            <td>
                <input type="text" name="name" size="25"
                       value="<c:out value='${reader.name}' />"
                />
                <input type="submit" value="Search"/>
            </td>
        </tr>
    </form>
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
                    <a href="/reader/edit?id=<c:out value='${reader.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/reader/delete?id=<c:out value='${reader.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>