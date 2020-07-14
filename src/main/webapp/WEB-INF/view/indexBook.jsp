<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Books</title>

</head>
<body>
<h1>Books Management</h1>
<h2>
    <a href="/book/new">Add New Book</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/book/list">List All Books</a>
    <br> <br>

    <h2>Find Book</h2>
    <form action="/book/find" method="post">
        <tr>
            <td>
                <input type="text" name="title" size="25"
                       value="<c:out value='${book.title}' />"
                />
                <input type="submit" value="Search"/>
            </td>
        </tr>
    </form>
</h2>

</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Books</h2></caption>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Publish year</th>
            <th>Number</th>
        </tr>
        <c:forEach var="book" items="${listBooks}">
            <tr>
                <td><c:out value="${book.id}" /></td>
                <td><c:out value="${book.title}" /></td>
                <td><c:out value="${book.authorName}" /></td>
                <td><c:out value="${book.genre}" /></td>
                <td><c:out value="${book.publishYear}" /></td>
                <td><c:out value="${book.number}" /></td>
                <td>
                    <a href="/book/edit?id=<c:out value='${book.id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/book/delete?id=<c:out value='${book.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>