<%-- 
    Document   : SearchByFullName
    Created on : Jul 2, 2021, 3:50:04 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEARCH</title>
    </head>
    <body>
        <font color="red" >
        Welcome, ${sessionScope.FULLNAME}
        </font>
        
        <h1>SEARCH</h1>
        <form action="searchButton">
            Search <input type="text" name="txtLastSearch" value="${param.txtLastSearch}" />
            <input type="submit" value="search" />
        </form>
         
    </body>
</html>
