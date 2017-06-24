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
        <a href="LogoutServlet">Log out</a><br/> <br/>
        <a href="manager.jsp">Back to management page</a>
        <h1>Meal Management</h1>
        
        <b>Add new meal</b> <br/>
        <form action="ManageMealServlet">
            Meal name: <input type="text" name="mealName" value="${param.mealName}" /> <br/>
            Unit: <input type="text" name="unit" value="${param.unit}" /> <br/>
            Price: <input type="text" name="price" value="${param.price}" /> <br/>
            Category: 

            <c:set var="cateItems" value="${requestScope.CATEGORY}"/>
            <select name="category">
                <c:forEach var="item" items="${cateItems}">
                    <option value="${item.id}">${item.name}</option>
                </c:forEach>
            </select> <br/>
            <input type="submit" value="Add" name="mealAction" />
        </form> <br/>
        
        <font color="red">${requestScope.ERROR}</font>
        
        <a href="MiddleServlet?btAction=ManageMeal">Reload Meals</a> <br/> <br/>
        
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
                                <a href="${removeLink}">Remove</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="mealAction" />
                            </td>
                        </tr>
                    </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
