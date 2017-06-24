<%-- 
    Document   : tableManagement
    Created on : 23-Jun-2017, 16:22:12
    Author     : VT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Table Management</title>
    </head>
    <body>
        Welcome, ${sessionScope.STAFF.name}<br/>
        <a href="LogoutServlet">Log out</a><br/> <br/>
        <a href="manager.jsp">Back to management page</a>
        <h1>Table Management</h1>
        
        <b>Add new table</b> <br/>
        <form action="ManageTableServlet">
            Table number: <input type="text" name="tableNumber" value="${param.tableNumber}" /> <br/>
            <font color="red">${requestScope.ERROR}</font>
            Capacity: <input type="text" name="capacity" value="${param.capacity}" /> <br/>
            <input type="submit" value="Add" name="tableAction" />
        </form> <br/>
        
        <c:set var="table" value="${requestScope.TABLE}"/>
        <c:if test="${not empty table}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Number</th>
                        <th>Capacity</th>
                        <td>Remove</td>
                        <td>Update</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${table}">
                    <form action="ManageTableServlet">
                        <tr>
                            <td>
                                ${dto.number}
                                <input type="hidden" name="tableNumber" value="${dto.number}" />
                            </td>
                            <td>
                                <input type="text" name="capacity" value="${dto.capacity}" />
                            </td>
                            <td>
                                <c:url var="removeLink" value="ManageTableServlet">
                                    <c:param name="tableAction" value="delete"/>
                                    <c:param name="tableNumber" value="${dto.number}"/>
                                </c:url>
                                <a href="${removeLink}">Remove</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="tableAction" />
                            </td>
                        </tr>
                    </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

    </body>
</html>
