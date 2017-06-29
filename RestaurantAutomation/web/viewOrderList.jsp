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
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="MiddleServlet">Restaurant Automation</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Welcome, ${sessionScope.STAFF.name}</a></li>
                    <c:if test="${sessionScope.STAFF.role eq 'waiter'}">
                        <li><a href="MiddleServlet">View tables</a></li>
                    </c:if>
                    <li><a href="LogoutServlet">Log out</a></li>
                </ul>
            </div>
        </nav>
        <c:set var="staff" value="${sessionScope.STAFF}"/>
                    
        <div class="container">
            <h1>Order list</h1>
            <c:if test="${not empty sessionScope.RESULT}">
                <table class="table table-striped">
                    <thead>
                        <tr>                        
                            <th>Table Number</th>
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
                            <c:if test="${((order.status eq 'ordered' or order.status eq 'cooking')
                                            and staff.role eq 'cook') or
                                          (order.status eq 'ready' and staff.role eq 'waiter')}">
                            <tr>
                            <form action="MiddleServlet">
                                <td>
                                    ${order.tableNumber}
                                    <input type="hidden" name="txtOrderId" value="${order.orderId}" />
                                    <input type="hidden" name="txtTableNumber" value="${order.tableNumber}" />
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
                                        <input type="submit" class="btn btn-success" value="Finish" name="btAction" />
                                    </td>
                                </c:if>
                                <c:if test="${order.status eq 'ordered'}">
                                    <td>
                                        <input type="submit" class="btn btn-info" value="Cook" name="btAction" />
                                    </td>
                                </c:if>
                                <c:if test="${order.status eq 'ready'}">
                                    <td>
                                        <input type="submit" class="btn btn-success" value="Serve" name="btAction" />
                                    </td>
                                </c:if>
                            </form>
                            </tr>
                            </c:if>
                        </c:forEach>                    
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty sessionScope.RESULT}">
                <h2>All orders were done</h2>
            </c:if>
        </div>
    </body>
</html>
