<%-- 
    Document   : Table
    Created on : Jun 21, 2017, 4:06:29 PM
    Author     : ahhun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Restaurant Automation</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>Table number</th>
                    <th>Capacity</th>
                    <th>Status</th>
                    <th colspan="2">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="tables" value="${requestScope.TABLES}"/>
                <c:forEach var="table" items="${tables}">
                <form action="MiddleServlet">
                    <input type="hidden" name="txtTableNumber" value="${table.number}" />
                    <tr>
                        <td>${table.number}</td>
                        <td>${table.capacity}</td>
                        <td>${table.status}</td>
                        <c:if test="${staff.role eq 'waiter'}">
                            <td><input type="submit" value="Take Order" name="btAction" /></td>
                            <td><input type="submit" value="Check Out" name="btAction" /></td>
                        </c:if>
                        <c:if test="${staff.role eq 'host'}">
                            <td><input type="submit" value="Use Table" name="btAction" /></td>
                        </c:if>
                        <c:if test="${staff.role eq 'busboy'}">
                            <td><input type="submit" value="Clean Table" name="btAction" /></td>
                        </c:if>
                    </tr>
                </form>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
