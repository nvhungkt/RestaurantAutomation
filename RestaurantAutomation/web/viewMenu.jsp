<%-- 
    Document   : viewMenu
    Created on : Jun 22, 2017, 1:39:04 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
    </head>
    <body>
        Welcome, ${sessionScope.STAFF.name}<br/>
        <a href="LogoutServlet">Log out</a><br/>
        <h1>Choose meal</h1>
        <c:set var="menu" value="${requestScope.MENU}"/>
        <c:if test="${not empty menu}">
            <form action="MiddleServlet">
                <input type="hidden" value="${param.txtTableNumber}" name="txtTableNumber"/>
                <input type="submit" value="Update order" name="btAction" />
				<input type="submit" value="View editable order" name="btAction" />
                <input type="submit" value="Add order" name="btAction" /><br/>
                <table border="1">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Unit</th>
                        <th>Quantity</th>
                        <th>price</th>
                    </tr>
                </thead>
                    <tbody>
                        <c:forEach var="dto" items="${menu}">
                            <tr>
                            <td>
                                ${dto.name}
                                <input type="hidden" name="txtMealID" value="${dto.id}" />
                            </td>
                            <td>${dto.unit}</td>
                            <td>
                                <c:set var="quantity" value="${param.txtLastQuantity}"/>
                                <input type="text" name="txtQuantity" value="0" />
                            </td>
                            <td>${dto.price}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </c:if>
        <c:if test="${empty menu}">
            <h2>
                <font>No Meal Found</font>
            </h2>
        </c:if>
    </body>
</html>
