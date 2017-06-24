<%-- 
    Document   : report
    Created on : 23-Jun-2017, 16:26:31
    Author     : VT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report</title>
    </head>
    <body>
        Welcome, ${sessionScope.STAFF.name}<br/>
        <a href="LogoutServlet">Log out</a><br/> <br/>
        <a href="manager.jsp">Back to management page</a>
        <h1>Report Export</h1>
        <a href="ReportServlet?reportAction=Revenue">Revenue Report</a> <br/>
        <a href="ReportServlet?reportAction=Efficiency">Efficiency Report</a> <br/>
        <a href="ReportServlet?reportAction=Time">Time Report</a> <br/>
        <a href="ReportServlet?reportAction=Menu">Menu Report</a> <br/> <br/> <br/>
        
        <c:if test="${not empty requestScope.REVENUEFLAG}">
            <p>Total revenue: $${requestScope.TOTAL}</p>
            <table border="1">
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Revenue</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="revenue" value="${requestScope.LIST}" />
                    <c:forEach var="item" items="${revenue}">
                        <tr>
                            <td>${item.date}</td>
                            <td>$${item.revenue}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty requestScope.EFFICIENCYFLAG}">
            <a href="ReportServlet?reportAction=Busboy">Busboy</a> <br/>
            <a href="ReportServlet?reportAction=Waiter">Waiter</a> <br/>
            <a href="ReportServlet?reportAction=Cook">Cook</a> <br/>
            
            <c:if test="${not empty requestScope.BUSBOY}">
                Busboy Efficiency: (# is rank) <br/> <br/>
                <c:forEach var="item" items="${requestScope.BUSBOY}" varStatus="counter">
                    #${counter.count}: ${item} <br/>
                </c:forEach>
            </c:if>
            <c:if test="${not empty requestScope.WAITER}">
                Waiter Efficiency: (# is rank) <br/> <br/>
                <c:forEach var="item" items="${requestScope.WAITER}" varStatus="counter">
                    #${counter.count}: ${item} <br/>
                </c:forEach>
            </c:if>
            <c:if test="${not empty requestScope.COOK}">
                Cook Efficiency: (# is rank) <br/> <br/>
                <c:forEach var="item" items="${requestScope.COOK}" varStatus="counter">
                    #${counter.count}: ${item} <br/>
                </c:forEach>
            </c:if>
        </c:if>
        <c:if test="${not empty requestScope.TIMEFLAG}">
            Average Turn-Around Time per guest: ${requestScope.TURNAROUND} minute(s) <br/>
            Average Preparation Time per meal: ${requestScope.PREPARATION} minute(s) <br/>
        </c:if>
        <c:if test="${not empty requestScope.MENUFLAG}">
            Most popular meal for now: <br/>
            <c:forEach var="item" items="${requestScope.MOSTPOPMEAL}">
                ${item} <br/> 
            </c:forEach>
        </c:if>
    </body>
</html>
