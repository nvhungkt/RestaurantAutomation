<%-- 
    Document   : checkOut
    Created on : Jun 25, 2017, 8:11:59 AM
    Author     : ahhun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check out</title>
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
                    <li><a href="ViewOrderListServlet">View completed meal</a></li>
                    <li><a href="LogoutServlet">Log out</a></li>
                </ul>
            </div>
        </nav>
                    
        <div class="container">
            <h2>Order</h2>
            <c:set var="order" value="${requestScope.ORDER}"/>
            <c:set var="total" value="${0}"/>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="orderDetail" items="${order.orderDetails}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${orderDetail.meal.name}</td>
                            <td>${orderDetail.meal.price}</td>
                            <td>${orderDetail.quantity}</td>
                            <td>${orderDetail.quantity * orderDetail.meal.price}</td>
                            <c:set var="total" value="${total + orderDetail.quantity * orderDetail.meal.price}"/>
                        </tr>
                    </c:forEach>
                        <tr>
                            <td colspan="3"></td>
                            <td>Total money</td>
                            <td>${total}</td>
                        </tr>
                </tbody>
            </table>
            <form action="MiddleServlet">
                <input type="hidden" name="txtTableNumber" value="${param.txtTableNumber}" />
                <input type="submit" class="btn btn-success" value="Finish check out" name="btAction"/>
            </form>
        </div>
    </body>
</html>
