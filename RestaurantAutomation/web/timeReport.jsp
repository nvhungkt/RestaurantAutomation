<%-- 
    Document   : timeReport
    Created on : Jul 15, 2017, 11:14:04 AM
    Author     : HT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Time report</title>
    </head>
    <body>
        <h1>Time Report</h1>
        <form action="TimeReportServlet">
            Type:
            <select name="reportType">
                <option value="0"
                        <c:if test="${param.reportType eq '0'}">selected</c:if>
                        >Turn-around time</option>
                <option value="1"
                        <c:if test="${param.reportType eq '1'}">selected</c:if>
                        >Preparation time</option>
            </select> <br/>
            
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

        <c:if test="${not empty TIMEINMONTH}">
            <h2>
                Average time this month:
            <font color="red">
            ${TIMEINMONTH}
            </font>
                minutes.
            </h2>
        </c:if>
            
        <c:if test="${not empty TIMEINDAY}">
            <h2>
                Average time in today:
            <font color="red">
            ${TIMEINDAY}
            </font>
                minutes.
            </h2>
        </c:if>

        <c:if test="${not empty ORDER}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Table</th>
                        <th>Arrived time</th>
                        <th>Leaved time</th>
                        <th>Average time (minute)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${ORDER}">
                        <tr>
                            <td>${dto.id}</td>
                            <td>${dto.tableNumber}</td>
                            <td>${dto.arrivingTime}</td>
                            <td>${dto.leavingTime}</td>
                            <td>
                                <c:if test="${dto.avgTime gt (TIMEINDAY * 1.5)}">
                                    <font color="red">
                                </c:if>
                                <c:if test="${dto.avgTime lt TIMEINDAY}">
                                    <font color="green">
                                </c:if>
                                ${dto.avgTime}
                                <c:if test="${dto.avgTime gt (TIMEINDAY * 1.5)}">
                                    </font>
                                </c:if>
                                <c:if test="${dto.avgTime lt TIMEINDAY}">
                                    </font>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
                        
        <c:if test="${not empty ORDERDETAIL}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Meal</th>
                        <th>Quantity</th>
                        <th>Cook</th>
                        <th>Average time (minute)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${ORDERDETAIL}">
                        <tr>
                            <td>${dto.orderID}</td>
                            <td>${dto.mealName}</td>
                            <td>${dto.quantity}</td>
                            <td>${dto.cookName}</td>
                            <td>
                                <c:if test="${dto.avgTime gt (TIMEINDAY * 1.5)}">
                                    <font color="red">
                                </c:if>
                                <c:if test="${dto.avgTime lt TIMEINDAY}">
                                    <font color="green">
                                </c:if>
                                ${dto.avgTime}
                                <c:if test="${dto.avgTime gt (TIMEINDAY * 1.5)}">
                                    </font>
                                </c:if>
                                <c:if test="${dto.avgTime lt TIMEINDAY}">
                                    </font>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
