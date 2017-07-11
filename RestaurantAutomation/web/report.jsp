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
            <h1>Report Export</h1>
            <ul class="pagination">
                <li><a href="RevenueServlet">Revenue Report</a></li>
                <li><a href="StaffReportServlet">Efficiency Report</a></li>
                <li><a href="ReportServlet?reportAction=Time">Time Report</a></li>
                <li><a href="ReportServlet?reportAction=Menu">Menu Report</a></li>
            </ul>
            <br/>
            
            <c:if test="${not empty requestScope.REVENUEFLAG}">
                <div class="panel panel-default">
                    <div class="panel-heading"><b>Revenue report</b></div>
                    <div class="panel-body">
                        <h3>Total revenue: $${requestScope.TOTAL}</h3>
                        <table class="table table-striped">
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
                    </div>
                </div>
            </c:if>
            
            <c:if test="${not empty requestScope.EFFICIENCYFLAG}">
                <ul class="pagination">
                    <li><a href="ReportServlet?reportAction=Busboy">Busboy</a></li>
                    <li><a href="ReportServlet?reportAction=Waiter">Waiter</a></li>
                    <li><a href="ReportServlet?reportAction=Cook">Cook</a></li>
                </ul>
                <br/>
                <div class="panel panel-default">
                    <div class="panel-heading"><b>Efficiency report</b></div>
                    <div class="panel-body">
                        <c:if test="${not empty requestScope.BUSBOY}">
                            <h4>Busboy Efficiency: (# is rank)</h4><br/>
                            <c:forEach var="item" items="${requestScope.BUSBOY}" varStatus="counter">
                                #${counter.count}: ${item} <br/>
                            </c:forEach>
                        </c:if>
                        <c:if test="${not empty requestScope.WAITER}">
                            <h4>Waiter Efficiency: (# is rank)</h4><br/>
                            <c:forEach var="item" items="${requestScope.WAITER}" varStatus="counter">
                                #${counter.count}: ${item} <br/>
                            </c:forEach>
                        </c:if>
                        <c:if test="${not empty requestScope.COOK}">
                            <h4>Cook Efficiency: (# is rank)</h4><br/>
                            <c:forEach var="item" items="${requestScope.COOK}" varStatus="counter">
                                #${counter.count}: ${item} <br/>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </c:if>
                        
            <c:if test="${not empty requestScope.TIMEFLAG}">
                <div class="panel panel-default">
                    <div class="panel-heading"><b>Efficiency report</b></div>
                    <div class="panel-body">
                        Average Turn-Around Time per guest: ${requestScope.TURNAROUND} minute(s) <br/>
                        Average Preparation Time per meal: ${requestScope.PREPARATION} minute(s) <br/>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty requestScope.MENUFLAG}">
                <div class="panel panel-default">
                    <div class="panel-heading"><b>Efficiency report</b></div>
                    <div class="panel-body">
                        Most popular meal for now: <br/>
                        <c:forEach var="item" items="${requestScope.MOSTPOPMEAL}">
                            ${item} <br/> 
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>
    </body>
</html>
