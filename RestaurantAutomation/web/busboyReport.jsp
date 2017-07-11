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
    </head>
    <body>
        <h1>Staff Report</h1>
        <form action="StaffReportServlet">
            Staff Role:
            <select name="staffRole">
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
            
            Year: 
            <select name="year">
                <c:forEach var="item" items="${YEAR}">
                    <option 
                        <c:if test="${param.year eq item}">
                            selected
                        </c:if>
                        >${item}</option>
                </c:forEach>
            </select> <br/>
            
            <c:if test="${not empty MONTH}">
                Month: 
                <select name="month">
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
            <input type="submit" value="Load" name="btAction"/>
        </form>
        
        <c:if test="${not empty RESULT}">
            <table border="1">
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
            <table border="1">
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
    </body>
</html>
