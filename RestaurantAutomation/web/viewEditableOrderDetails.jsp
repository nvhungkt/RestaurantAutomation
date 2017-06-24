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
    </head>
    <body>
        <c:url var="viewMenuPage" value="MiddleServlet">
            <c:param name="btAction" value="Take Order"/>
            <c:param name="txtTableNumber" value="${param.txtTableNumber}"/>
        </c:url>
        <h1>Edit order</h1>
        <c:set var="listOrderDetail" value="${requestScope.ORDER.orderDetails}"/>
        <c:if test="${not empty listOrderDetail}">
        <a href="${viewMenuPage}" style="float: right">Click here to re-view menu</a>
            <%--<c:set var="haveEditable" value="false"/>
            <c:forEach var="dto" items="${listOrderDetail}">
                <c:if test="${dto.status eq 'ordered'}">
                    <c:set var="haveEditable" value="true"/>
                </c:if>
            </c:forEach>
            <c:if test="${haveEditable eq 'true'}">--%>
                <form action="MiddleServlet">
                    <input type="submit" value="Remove order" name="btAction" />
                    <input type="submit" value="Update order" name="btAction" /><br/><br/>
                    <input type="hidden" value="${param.txtTableNumber}" name="txtTableNumber"/>
                    <table border="1">
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
                                            <input type="text" name="txtEditableQuantity" value="${dto.quantity}" />
                                        </td>
                                        <td>
                                            ${dto.status}
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkRemove" value="${dto.no}" />
                                        </td>
                                    </tr>
                                <%--</c:if>--%>
                            </c:forEach>
                        </tbody>
                    </table>
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
    </body>
</html>
