<%-- 
    Document   : revenueReport
    Created on : Jul 6, 2017, 9:48:00 PM
    Author     : HT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Revenue Report</title>
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
                    <li><a href="MiddleServlet?btAction=ManageTable">Manage tables now</a></li>
                    <li><a href="MiddleServlet?btAction=ManageMeal">Manage meals now</a></li>
                    <li><a href="MiddleServlet?btAction=report">Export Report</a></li>
                    <li><a href="LogoutServlet">Log out</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <h1>Revenue Report</h1>
        
        <form action="RevenueServlet">
            <label for="year">Year:</label>
            <select class="form-control" id="year" name="year">
                <c:forEach var="item" items="${YEAR}">
                    <option 
                        <c:if test="${param.year eq item}">
                            selected
                        </c:if>
                        >${item}</option>
                </c:forEach>
            </select> <br/>
            
            <c:if test="${not empty MONTH}">
                <label for="month">Month:</label>
                <select class="form-control" id="month" name="month">
                    <option></option>
                    <c:forEach var="item" items="${MONTH}">
                        <option
                            <c:if test="${param.month eq item}">
                                selected
                            </c:if>
                            >${item}</option>
                    </c:forEach>
                </select> <br/>
            </c:if>
                
            <c:if test="${not empty DAY}">
                <label for="day">Day:</label>
                <select class="form-control" id="day" name="day">
                    <option></option>
                    <c:forEach var="item" items="${DAY}">
                        <option
                            <c:if test="${param.day eq item}">
                                selected
                            </c:if>
                            >${item}</option>
                    </c:forEach>
                </select> <br/>
            </c:if>
                
            <input type="submit" value="Load" name="btAction" class="btn btn-success"/>
        </form>

        <c:if test="${not empty param.year and empty param.month}">
            <h2>Revenue of year ${param.year}: <font color="red">${YEARREVENUE}</font> </h2>
        </c:if>
            
        <c:if test="${not empty param.month and empty param.day}">
            <h2>Revenue of month ${param.month}: <font color="red">${MONTHREVENUE}</font> </h2>
        </c:if>
               
        <c:if test="${not empty param.day}">
            <h2>Revenue of this day: <font color="red">${DAYREVENUE}</font> </h2>
        </c:if>

        <c:if test="${not empty ORDERLIST}">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Total bill</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${ORDERLIST}">
                        <tr>
                            <td>${entry.key}</td>
                            <td>${entry.value}</td>
                            <td>
                                <c:url var="viewLink" value="RevenueServlet">
                                    <c:param name="orderID" value="${entry.key}"/>
                                    <c:param name="year" value="${param.year}"/>
                                    <c:param name="month" value="${param.month}"/>
                                    <c:param name="day" value="${param.day}"/>
                                    <c:param name="btAction" value="viewOrderDetail"/>
                                </c:url>
                                <a href="${viewLink}">View detail</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${not empty ORDERDETAIL}">
            <b>Order ID: ${ORDERDETAIL[0].orderID}</b>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Meal name</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${ORDERDETAIL}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.mealName}</td>
                            <td>${dto.quantity}</td>
                            <td>${dto.price}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
            
        </div>
    </body>
</html>
