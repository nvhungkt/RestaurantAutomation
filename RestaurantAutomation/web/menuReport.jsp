<%-- 
    Document   : menuReport
    Created on : Jul 16, 2017, 6:17:02 PM
    Author     : HT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Report</title>
    </head>
    <body>
        <h1>Menu Report</h1>
        <form action="MenuReportServlet">
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
        
        <c:if test="${not empty YEARMEAL}">
            <h2>
                The most popular meal in this year:
                <font color="red">
                <c:forEach var="dto" items="${YEARMEAL}">
                    <br/> ${dto.key} (${dto.value} times)
                </c:forEach>
                </font>
            </h2>
        </c:if>
        <c:if test="${not empty MONTHMEAL}">
            <h2>
                The most popular meal in this month:
                <font color="red">
                <c:forEach var="dto" items="${MONTHMEAL}">
                    <br/> ${dto.key} (${dto.value} times)
                </c:forEach>
                </font>
            </h2>
        </c:if>
        <c:if test="${not empty DAYMEAL}">
            <h2>
                The most popular meal in today:
                <font color="red">
                <c:forEach var="dto" items="${DAYMEAL}">
                    <br/> ${dto.key} (${dto.value} times)
                </c:forEach>
                </font>
            </h2>
        </c:if>
        
        <c:if test="${not empty LIST}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Meal</th>
                        <th>Times</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${LIST}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.value.mealName}</td>
                            <td>${dto.value.times}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
