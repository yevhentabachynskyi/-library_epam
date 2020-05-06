<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Books</title>

</head>
<body>
<h1>Book Management</h1>
<h2>
    <a href="/book/new">Add New Book</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/book/list">List All Books</a>

</h2>
</center>
<div align="center">
    <c:if test="${book != null}">
    <form action="/book/update" method="post">
        </c:if>
        <c:if test="${book == null}">
        <form action="/book/add" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${book != null}">
                            Edit book
                        </c:if>
                        <c:if test="${book == null}">
                            Add New Book
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${book != null}">
                    <input type="hidden" name="id" value="<c:out value='${book.id}' />"/>
                </c:if>
                <tr>
                    <th>Title:</th>
                    <td>
                        <input type="text" name="title" size="55"
                               value="<c:out value='${book.title}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Author:</th>
                    <td>
                        <input type="text" name="authorName" size="55"
                               value="<c:out value='${book.authorName}' />"
                        />
                    </td>
                </tr>
                <th>Genre:</th>
                <td>
                    <%--                    <input type="text" name="genre" size="45"--%>
                    <%--                           value="<c:out value='${book.genre}' />"--%>
                    <%--                    />--%>
                    <%--                <input type="checkbox" name="genre" value="${book.genre}">--%>
                    <%--                <label>--%>
                    <%--                    <input type="checkbox"/> ${book.genre}--%>
                    <%--                </label>--%>
                        <input type="checkbox" name="genre" value="NOVEL"/>NOVEL
                        <input type="checkbox" name="genre" value="FANTASY"/>FANTASY
                        <input type="checkbox" name="genre" value="HISTORY"/>HISTORY
                        <input type="checkbox" name="genre" value="DRAMA"/>DRAMA
                        <input type="checkbox" name="genre" value="LYRICS"/>LYRICS

                </td>
                </tr>
                <tr>
                    <th>Year:</th>
                    <td>
                        <input type="number" name="publishYear" size="4"
                               value="<c:out value='${book.publishYear}' />"
                        />
                    </td>
                </tr>
                <tr>
                <tr>
                    <th>Number:</th>
                    <td>
                        <input type="number" name="number" size="11"
                               value="<c:out value='${book.number}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>