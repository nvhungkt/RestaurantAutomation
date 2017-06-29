<%-- 
    Document   : viewEditableOrderDetails
    Created on : Jun 23, 2017, 1:01:41 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit order</title>
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
                    <li><a href="ViewOrderListServlet">View completed meal</a></li>
                    <li><a href="LogoutServlet">Log out</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <c:url var="viewMenuPage" value="MiddleServlet">
                <c:param name="btAction" value="Take Order"/>
                <c:param name="txtTableNumber" value="${param.txtTableNumber}"/>
            </c:url>
            <h2>Edit order</h2>
            <c:set var="listOrderDetail" value="${requestScope.ORDER.orderDetails}"/>
            <c:if test="${not empty listOrderDetail}">
            <a role="button" class="btn btn-default" href="${viewMenuPage}" style="float: right">Re-view Menu</a>
                <%--<c:set var="haveEditable" value="false"/>
                <c:forEach var="dto" items="${listOrderDetail}">
                    <c:if test="${dto.status eq 'ordered'}">
                        <c:set var="haveEditable" value="true"/>
                    </c:if>
                </c:forEach>
                <c:if test="${haveEditable eq 'true'}">--%>
                <form action="MiddleServlet" method="POST">
                        <input type="hidden" value="${param.txtTableNumber}" name="txtTableNumber"/>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Status</th>
                                    <th>Check Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${listOrderDetail}">
                                    <%--<c:if test="${dto.status eq 'ordered'}">--%>
                                        <tr>
                                            <td>
                                                ${dto.meal.name}
                                                <input type="hidden" name="txtOrderDetailID" value="${dto.no}" />
                                            </td>
                                            <td>
                                                <input type="number" name="txtEditableQuantity" value="${dto.quantity}" />
                                            </td>
                                            <td>
                                                ${dto.status}
                                            </td>
                                            <td>
                                                <input type="checkbox" name="chkRemove" value="${dto.no}"
                                                       <c:if test="${dto.status ne 'ordered'}">disabled="disabled"</c:if>
                                                           />
                                            </td>
                                        </tr>
                                    <%--</c:if>--%>
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="submit" class="btn btn-success" value="Update order" name="btAction" />
                        <input type="submit" class="btn btn-danger" value="Remove order" name="btAction" />
                    </form>
                <%--</c:if>
                <c:if test="${haveEditable eq 'false'}">
                    <h3>
                        <font color="red">No Order editable !!!</font>
                    </h3>
                </c:if>--%>
            </c:if>
            <c:if test="${empty listOrderDetail}">
                <h3>
                    <font color="red">No Order exist !!!</font>
                </h3>
            </c:if>
        </div>
    </body>
</html>
