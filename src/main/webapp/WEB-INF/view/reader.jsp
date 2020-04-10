<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LIBRARY</title>

</head>
<body>
<h1>Reader Management</h1>
<h2>
    <a href="new">Add New reader</a>
    &nbsp;&nbsp;&nbsp;
    <a href="list">List All reader</a>

</h2>
</center>
<div align="center">
    <c:if test="${reader != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${reader == null}">
        <form action="add" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${reader != null}">
                            Edit reader
                        </c:if>
                        <c:if test="${reader == null}">
                            Add New reader
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${reader != null}">
                    <input type="hidden" name="id" value="<c:out value='${reader.id}' />"/>
                </c:if>
                <tr>
                    <th>Name:</th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${reader.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Address:</th>
                    <td>
                        <input type="text" name="address" size="45"
                               value="<c:out value='${reader.address}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Phone:</th>
                    <td>
                        <input type="number" name="phone" size="11"
                               value="<c:out value='${reader.phone}' />"
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