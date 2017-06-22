<%-- 
    Document   : inputTableNumber
    Created on : Jun 22, 2017, 6:30:11 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Input table number</title>
    </head>
    <body>
        <h1>Input table number</h1>
        <form action="MiddleServlet">
            <input type="text" name="txtInputTableNum" value="" /><br/>
            <input type="hidden" name="txtTask" value="${param.txtTask}" />
            <input type="submit" value="Choose Meal" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
