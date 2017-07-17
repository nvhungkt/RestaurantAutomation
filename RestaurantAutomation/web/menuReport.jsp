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
            <h1>Menu Report</h1>
            <form action="MenuReportServlet">
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

            <c:if test="${not empty YEARMEAL}">
                <h4>
                    The most popular meal in this year:
                    <font color="red">
                    <c:forEach var="dto" items="${YEARMEAL}">
                        <br/> ${dto.key} (${dto.value} times)
                    </c:forEach>
                    </font>
                </h4>
            </c:if>
            <c:if test="${not empty MONTHMEAL}">
                <h4>
                    The most popular meal in this month:
                    <font color="red">
                    <c:forEach var="dto" items="${MONTHMEAL}">
                        <br/> ${dto.key} (${dto.value} times)
                    </c:forEach>
                    </font>
                </h4>
            </c:if>
            <c:if test="${not empty DAYMEAL}">
                <h4>
                    The most popular meal in today:
                    <font color="red">
                    <c:forEach var="dto" items="${DAYMEAL}">
                        <br/> ${dto.key} (${dto.value} times)
                    </c:forEach>
                    </font>
                </h4>
            </c:if>

            <c:if test="${not empty LIST}">
                <table class="table table-striped">
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
        </div>
    </body>
</html>
