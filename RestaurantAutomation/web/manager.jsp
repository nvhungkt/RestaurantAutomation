<%-- 
    Document   : manager
    Created on : 23-Jun-2017, 16:23:38
    Author     : VT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager</title>
    </head>
    <body>
        Welcome, ${sessionScope.STAFF.name}<br/>
        <a href="LogoutServlet">Log out</a><br/>
        <h1>Manager Page</h1>
        <a href="MiddleServlet?btAction=ManageTable">Manage tables now</a> <br/>
        <a href="MiddleServlet?btAction=ManageMeal">Manage meals now</a> <br/>
        <a href="MiddleServlet?btAction=report">Export Report</a>
    </body>
</html>
