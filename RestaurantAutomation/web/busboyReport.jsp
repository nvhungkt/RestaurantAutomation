<%-- 
    Document   : staffReport
    Created on : Jul 11, 2017, 9:11:31 PM
    Author     : HT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Report</title>
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
            <h1>Staff Report</h1>
            <form action="StaffReportServlet">
                <label for="staffRole">Staff Role:</label>
                <select class="form-control" id="staffRole" name="staffRole">
                    <option value="0"
                            <c:if test="${param.staffRole eq '0'}">selected</c:if>
                            >Bus boy</option>
                    <option value="1"
                            <c:if test="${param.staffRole eq '1'}">selected</c:if>
                            >Waiter</option>
                    <option value="2"
                            <c:if test="${param.staffRole eq '2'}">selected</c:if>
                            >Cook</option>
                </select> <br/>

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
                <input type="submit" value="Load" name="btAction" class="btn btn-success"/>
            </form>

            <c:if test="${not empty RESULT}">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Work Times</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="entry" items="${RESULT}" varStatus="counter">
                            <tr>
                                <td>
                                    <c:if test="${counter.count eq 1}"><font color="red"></c:if>
                                    ${entry.value.id}
                                    <c:if test="${counter.count eq 1}"></font></c:if>
                                </td>
                                <td>
                                    <c:if test="${counter.count eq 1}"><font color="red"></c:if>
                                    ${entry.value.name}
                                    <c:if test="${counter.count eq 1}"></font></c:if>
                                </td>
                                <td>
                                    <c:if test="${counter.count eq 1}"><font color="red"></c:if>
                                    ${entry.value.role}
                                    <c:if test="${counter.count eq 1}"></font></c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${not empty STAFFLIST}">
                <h2>All staffs</h2>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>ID</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${STAFFLIST}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${dto.id}</td>
                                <td>${dto.name}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </body>
</html>
