<%-- 
    Document   : checkoutView
    Created on : Jul 6, 2021, 4:24:53 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CHECKOUT</title>
    </head>
    <body>
        <h1>YOUR BILL</h1>
        <c:set var="bill" value="${requestScope.LIST_ITEMS_IN_CHECKOUT_VIEW}"/>
        <c:if test="${not empty bill}">
            <table border="1">
                <thead>
                    <tr>
                        <th>NO</th>
                        <th>NAME</th>
                        <th>QUANTITY</th>
                        <th>PRICE</th>
                        <th>PRICE</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${bill}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${item.name}</td>
                        <td>${item.quantity}</td>
                        <td>${item.price}</td>
                        <td>${item.total}</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="viewList">Back To Shopping</a>
        </c:if>
        <c:if test="${empty bill}">
            <h2>Check Out Unsuccessfully</h2>
            <a href="viewList">Back To Shopping</a>
        </c:if>
    </body>
</html>
