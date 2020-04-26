<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LIBRARY</title>
    <style>
        body { background: url("/WEB-INF/view/image/library.jpg"); }
    </style>
</head>

<body>

<%--<form action="/reader" method="post">
    <input type="submit"name="reader" value="Readers Management"/>
</form>
<form action="book.jsp" method="post">
    <input type="submit"name="book" value="Books Management"/>
</form>--%>
<h2>
    <a href="/reader">Readers Management</a>
</h2>
<h2>
    <a href="/book">All Books</a>
</h2>
<h2>
    <a href="/author">Authors</a>
</h2>
<h2>
    <a href="/give">Giving List</a>
</h2>
</body>
</html>
