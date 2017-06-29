<%-- 
    Document   : mealManagement
    Created on : 23-Jun-2017, 16:25:31
    Author     : VT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meal Management</title>
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
            <h1>Meal Management</h1>

            <div class="panel panel-default">
                <div class="panel-heading"><b>Add new meal</b></div>
                <div class="panel-body">
                    <form action="ManageMealServlet">
                        <label for="mealName">Meal name:</label>
                        <input id="mealName" class="form-control" type="text" name="mealName" value="${param.mealName}" /> <br/>
                        <label for="unit">Unit:</label>
                        <input id="unit" class="form-control" type="text" name="unit" value="${param.unit}" /> <br/>
                        <label for="price">Price:</label>
                        <input id="price" class="form-control" type="text" name="price" value="${param.price}" /> <br/>
                        <label for="category">Category:</label>
                        <c:set var="cateItems" value="${requestScope.CATEGORY}"/>
                        <select id="category" class="form-control" name="category">
                            <c:forEach var="item" items="${cateItems}">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select> <br/>
                        <input type="submit" class="btn btn-success" value="Add" name="mealAction" />
                    </form>
                </div>
            </div>

            <font color="red">${requestScope.ERROR}</font>

            <c:set var="meal" value="${requestScope.MEAL}"/>
            <c:if test="${not empty meal}">
                <div class="panel panel-default">
                    <div class="panel-heading"><b>Edit/Remove meal</b></div>
                    <div class="panel-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <td>Unit</td>
                                    <td>Price</td>
                                    <td>Category</td>
                                    <td>Remove</td>
                                    <td>Update</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${meal}">
                                <form action="ManageMealServlet">
                                    <tr>
                                        <td>
                                            ${dto.id}
                                            <input type="hidden" name="mealID" value="${dto.id}" />
                                        </td>
                                        <td>
                                            <input type="text" name="mealName" value="${dto.name}" />
                                        </td>
                                        <td>
                                            <input type="text" name="unit" value="${dto.unit}" />
                                        </td>
                                        <td>
                                            <input type="text" name="price" value="${dto.price}" />
                                        </td>
                                        <td>
                                            <select name="category">
                                                <c:forEach var="item" items="${cateItems}">
                                                    <option value="${item.id}"
                                                            <c:if test="${dto.cateID eq item.id}">
                                                                selected
                                                            </c:if>
                                                                >
                                                        ${item.name}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <c:url var="removeLink" value="ManageMealServlet">
                                                <c:param name="mealAction" value="Delete"/>
                                                <c:param name="mealID" value="${dto.id}"/>
                                            </c:url>
                                            <a href="${removeLink}" class="btn btn-danger" role="button">Remove</a>
                                        </td>
                                        <td>
                                            <input type="submit" class="btn btn-success" value="Update" name="mealAction" />
                                        </td>
                                    </tr>
                                </form>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
    </body>
</html>
