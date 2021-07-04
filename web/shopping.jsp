<%-- 
    Document   : shopping
    Created on : Jul 4, 2021, 8:59:15 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping</title>
    </head>
    <body>
        <c:set var="listItems" value="${requestScope.LIST_ITEM}}"/>
        <h1>Shopping</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items=""/>
            </tbody>
        </table>

    </body>
</html>
