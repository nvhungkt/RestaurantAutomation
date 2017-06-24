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
    </head>
    <body>
        Welcome, ${sessionScope.STAFF.name}<br/>
        <a href="LogoutServlet">Log out</a><br/>
        <h1>Meal Management</h1>
        
        <b>Add new meal</b> <br/>
        <form action="ManageMealServlet">
            Meal name: <input type="text" name="mealName" value="${param.mealName}" /> <br/>
            Unit: <input type="text" name="unit" value="${param.unit}" /> <br/>
            Price: <input type="text" name="price" value="${param.price}" /> <br/>
            <font color="red">${requestScope.ERROR}</font>
            Category: 
            
            <c:set var="cateItems" value="${requestScope.CATEGORY}"/>
            <select name="category">
                <c:forEach var="item" items="${cateItems}">
                    <option>${item}</option>
                </c:forEach>
            </select> <br/>
            <input type="submit" value="Add" name="mealAction" />
        </form> <br/>
        
        <c:set var="meal" value="${requestScope.MEAL}"/>
        <c:if test="${not empty meal}">
            <table border="1">
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
                        
                    </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
