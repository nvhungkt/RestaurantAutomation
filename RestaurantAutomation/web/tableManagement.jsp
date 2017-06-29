<%-- 
    Document   : tableManagement
    Created on : 23-Jun-2017, 16:22:12
    Author     : VT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Table Management</title>
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
            <h1>Table Management</h1>
            
            <div class="panel panel-default">
                <div class="panel-heading"><b>Add new table</b></div>
                <div class="panel-body">
                    <form action="ManageTableServlet">
                        <label for="tableNumber">Table number:</label>
                        <input id="tableNumber" class="form-control" type="text" name="tableNumber" value="${param.tableNumber}" />
                        <br/>
                        <font color="red">${requestScope.ERROR}</font>
                        <label for="capacity">Capacity:</label>
                        <input id="capacity" class="form-control" type="text" name="capacity" value="${param.capacity}" />
                        <br/>
                        <input type="submit" class="btn btn-success" value="Add" name="tableAction" />
                    </form>
                </div>
            </div>
            
            <div class="panel panel-default">
                <div class="panel-heading"><b>Edit/Remove table</b></div>
                <div class="panel-body">
                <c:set var="table" value="${requestScope.TABLE}"/>
                <c:if test="${not empty table}">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Number</th>
                                <th>Capacity</th>
                                <td>Remove</td>
                                <td>Update</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${table}">
                            <form action="ManageTableServlet">
                                <tr>
                                    <td>
                                        ${dto.number}
                                        <input type="hidden" name="tableNumber" value="${dto.number}" />
                                    </td>
                                    <td>
                                        <input type="number" name="capacity" value="${dto.capacity}" />
                                    </td>
                                    <td>
                                        <c:url var="removeLink" value="ManageTableServlet">
                                            <c:param name="tableAction" value="delete"/>
                                            <c:param name="tableNumber" value="${dto.number}"/>
                                        </c:url>
                                        <a href="${removeLink}" class="btn btn-danger" role="button">Remove</a>
                                    </td>
                                    <td>
                                        <input type="submit" class="btn btn-success" value="Update" name="tableAction" />
                                    </td>
                                </tr>
                            </form>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
