<%-- 
    Document   : viewOrderList
    Created on : Jun 22, 2017, 11:21:05 AM
    Author     : Turtle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Orders</title>
    </head>
    <body>
        Welcome, ${sessionScope.STAFF.name}</br>
        <a href="LogoutServlet">Log out</a></br>
        
        <h2>Here is order list</h2>         
        <c:if test="${not empty sessionScope.RESULT}">
            <table border="1">
                <thead>
                    <tr>                        
                        <th>Order ID</th>
                        <th>Order No</th>
                        <th>Meal Name</th>
                        <th>Meal Unit</th>
                        <th>Quantity</th>
                        <th>Category</th>
                        <th>Taken Time</th>
                        <th>Status</th>
                        <th>Button</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${sessionScope.RESULT}">
                        <tr>
                    <form action="MiddleServlet">
                        <td>
                            ${order.orderId}
                            <input type="hidden" name="txtOrderId" value="${order.orderId}" />
                        </td>
                        <td>
                            ${order.no}
                            <input type="hidden" name="txtOrderNo" value="${order.no}" />
                        </td>
                        <td>${order.mealName}</td>
                        <td>${order.mealUnit}</td>
                        <td>${order.quantity}</td>
                        <td>${order.category}</td>
                        <td>${order.takenTime}</td>
                        <td>${order.status}</td>
                        <c:if test="${order.status eq 'cooking'}">
                            <td>
                                <input type="submit" value="Finish" name="btAction" />
                            </td>
                        </c:if>
                        <c:if test="${order.status eq 'ordered'}">
                            <td>
                                <input type="submit" value="Cook" name="btAction" />
                            </td>
                        </c:if>
                    </form>
                </tr>
            </c:forEach>                    
        </tbody>
    </table>
</c:if>
<c:if test="${empty sessionScope.RESULT}">
    <h2>All orders were cooked</h2>
</c:if>

</body>
</html>
