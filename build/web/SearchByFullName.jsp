<%-- 
    Document   : SearchByFullName
    Created on : Jul 2, 2021, 3:50:04 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <form action="searchButton" method="POST">
            Search <input type="text" name="txtLastSearch" value="${param.txtLastSearch}" />
            <input type="submit" value="search" />
        </form>
            <c:set var="searchName" value="${param.txtLastSearch}"/>
            <c:if test="${not empty searchName}">
                <c:set var="list_search_name" value="${requestScope.LIST_SEARCHED_ACCOUNT}"/>
                <c:if test="${not empty list_search_name}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>FullName</th>
                                <th>isAdmin</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${list_search_name}" varStatus="counter">
                                <tr>
                                <td>${counter.count}</td>
                                <td>${dto.username}</td>
                                <td>${dto.password}</td>
                                <td>${dto.fullname}</td>
                                <td>${dto.isAdmin}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
            </c:if>
    </body>
</html>
