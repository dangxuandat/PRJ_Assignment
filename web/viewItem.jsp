<%-- 
    Document   : viewItem
    Created on : Jul 4, 2021, 7:45:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h1>View Your Cart</h1>
        <c:set var="listItem" value="${requestScope.LIST_ITEM_IN_VIEW}"/>
        <c:set var="error" value="${requestScope.error}"/>
        <c:if test="${not empty listItem}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${listItem}" varStatus="counter">
                        <tr>
                        <td>${counter.count}</td>
                        <th>${item.name}</th>
                        <th>${item.price}</th>
                        <th>${item.quantity}</th>
                        <th>${item.total}</th>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty error}">
            <h2>${error}</h2>
            <a href="viewList">Go Shopping</a>
        </c:if>
    </body>
</html>
