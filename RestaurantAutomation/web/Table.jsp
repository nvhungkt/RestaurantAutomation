<%-- 
    Document   : Table
    Created on : Jun 21, 2017, 4:06:29 PM
    Author     : ahhun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Table</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Restaurant Automation</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Welcome, ${sessionScope.STAFF.name}</a></li>
                    <li><a href="LogoutServlet">Log out</a></li>
                </ul>
            </div>
        </nav>

        <div class="container">
            <h2>Table</h2>            
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Table number</th>
                        <th>Capacity</th>
                        <th>Status</th>
                        <th colspan="2">Action</th>
                    </tr>
                </thead>
                
                <tbody>
                    <c:set var="tables" value="${requestScope.TABLES}"/>
                    <c:set var="staff" value="${sessionScope.STAFF}"/>
                    <c:forEach var="table" items="${tables}">
                    <form action="MiddleServlet">
                        <input type="hidden" name="txtTableNumber" value="${table.number}" />
                        <tr>
                            <td>${table.number}</td>
                            <td>${table.capacity}</td>
                            <td>${table.status}</td>
                            <c:if test="${staff.role eq 'waiter'}">
                                <td><input type="submit" value="Take Order" class="btn btn-success"
                                           <c:if test="${table.status ne 'occupied'}">disabled="disabled"</c:if>
                                           name="btAction" /></td>
                                <td><input type="submit" value="Check Out" class="btn btn-info"
                                           <c:if test="${table.status ne 'occupied'}">disabled="disabled"</c:if>
                                           name="btAction" /></td>
                            </c:if>
                            <c:if test="${staff.role eq 'host'}">
                                <td><input type="submit" value="Use Table" class="btn btn-success"
                                           <c:if test="${table.status ne 'cleaned'}">disabled="disabled"</c:if>
                                           name="btAction" /></td>
                            </c:if>
                            <c:if test="${staff.role eq 'busboy'}">
                                <td><input type="submit" value="Clean Table" class="btn btn-success"
                                           <c:if test="${table.status ne 'dirty'}">disabled="disabled"</c:if>
                                           name="btAction" /></td>
                            </c:if>
                        </tr>
                    </form>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
