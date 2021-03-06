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
        <title>Menu</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            <h1>Choose meal</h1>
            <a href="MiddleServlet" role="button" class="btn btn-default" style="float: right">Re-view Table</a>
            <c:set var="menu" value="${requestScope.MENU}"/>
            <c:if test="${not empty menu}">
                <form action="MiddleServlet" method="POST">
                    <input type="hidden" value="${param.txtTableNumber}" name="txtTableNumber"/>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Unit</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                        <tbody>
                            <c:forEach var="dto" items="${menu}">
                                <tr>
                                <td>
                                    ${dto.name}
                                    <input type="hidden" name="txtMealID" value="${dto.id}" />
                                </td>
                                <td>
                                    <c:set var="quantity" value="${param.txtLastQuantity}"/>
                                    <input type="number" name="txtQuantity" value="0" />
                                </td>
                                <td>${dto.unit}</td>
                                <td>${dto.price}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <input type="submit" class="btn btn-info" value="View editable order" name="btAction" />
                    <input type="submit" class="btn btn-success" value="Add order" name="btAction" /><br/>
                </form>
            </c:if>
            <c:if test="${empty menu}">
                <h2>
                    <font>No Meal Found</font>
                </h2>
            </c:if>
        </div>
    </body>
</html>
