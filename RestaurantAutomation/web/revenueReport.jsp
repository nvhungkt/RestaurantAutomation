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
    </head>
    <body>
        <h1>Revenue Report</h1>
        <form action="RevenueServlet">
            Year: 
            <select name="year">
                <c:forEach var="item" items="${YEAR}">
                    <option 
                        <c:if test="${param.year eq item}">
                            selected
                        </c:if>
                        >${item}</option>
                </c:forEach>
            </select> <br/>
            
            <c:if test="${not empty MONTH}">
                Month: 
                <select name="month">
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
                Day: 
                <select name="day">
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
                
            <input type="submit" value="Load" name="btAction"/>
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
            <table border="1">
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
            Order ID: ${ORDERDETAIL[0].orderID}
            <table border="1">
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
    </body>
</html>
