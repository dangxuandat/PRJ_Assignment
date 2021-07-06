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
        <c:set var="listItem" value="${sessionScope.LIST_ITEM_IN_VIEW}"/>
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
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <form action="removeItem">
                    <c:forEach var="item" items="${listItem}" varStatus="counter">
                    
                    <tr>
                        <td>${counter.count}</td>
                        <td>
                            ${item.name}
                            <input type="hidden" name="txtItemName" value="${item.name}" />
                        </td>
                        <td>${item.price}</td>
                        <td>${item.quantity}</td>
                        <td>${item.total}</td>
                        <td>
                            <input type="checkbox" name="checkedItem" value="${item.name}"/>
                        </td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5">
                            <a href="viewList">Add more items</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Item" />
                        </td>
                    </tr>
                    </form>
                </tbody>
            </table>
                    <form action="checkoutButton">
                        <input type="submit" value="Check Out"/>
                    </form>
        </c:if>
        <c:if test="${not empty error}">
            <h2>${error}</h2>
            <a href="viewList">Go Shopping</a>
        </c:if>
    </body>
</html>
