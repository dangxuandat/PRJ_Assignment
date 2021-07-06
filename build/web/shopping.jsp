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
        <h1>Shopping</h1>
        <c:set var="listItems" value="${requestScope.LIST_ITEM}"/>
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Amount</th>
                        <th>Add to cart</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${listItems}" varStatus="counter">
                    <form action="addItemButton">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${item.name}
                                <input type="hidden" name="txtItemName" value="${item.name}" />
                            </td>
                            <td>
                                ${item.price}
                                
                            </td>
                            <td>
                                ${item.quantity}
                            </td>
                            <td>
                                <input type="submit" value="AddItemToCart" />
                            </td>
                        </tr>
                    </form>
                    </c:forEach>
                    <form action = "viewItemInCart">
                        <input type="submit" value="ViewCart" />
                    </form>
                </tbody>
            </table>
            <a href="login.html">Back to Login</a>
    </body>
</html>
