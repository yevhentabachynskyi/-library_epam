<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>Return</title>

</head>
<body>
<h1>List all books</h1>

</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Books </h2></caption>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Giving date</th>
            <th>Return date</th>
        </tr>
        <c:forEach var="map" items="${giveMap}">
            <tr>
                <td><c:out value="${(map.value).id}"/></td>
                <td><c:out value="${(map.value).title}"/></td>
                <td><c:out value="${(map.key).dateGive}"/></td>
                <td><c:out value="${(map.key).dateReturn}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>