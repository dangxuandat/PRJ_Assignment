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
        <form action="searchButton">
            Search <input type="text" name="txtLastSearch" value="${param.txtLastSearch}" />
            <input type="submit" value="search" />
        </form>
            <c:set var="searchName" value="${param.txtLastSearch}"/>
            <c:set var="error" value="${sessionScope.error}"/>
            <c:set var="deleteError" value="${sessionScope.deleteError}"/>
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
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${list_search_name}" varStatus="counter">
                            <form action="updateButton">
                                <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>${dto.fullname}</td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON"
                                           <c:if test="${dto.admin}">
                                               checked="checked"
                                           </c:if>
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="deleteButton">
                                        <c:param name="txtUsername" value="${dto.username}"/>
                                        <c:param name="txtLastSearch" value="${searchName}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" />
                                    <input type="hidden" name="txtLastSearch" value="${searchName}"/>
                                </td>
                            </tr>
                            </form>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${not empty error}">
                        <font color="red">
                            ${error.passwordLengthError}
                        </font>
                    </c:if>
                <c:if test="${not empty deleteError}">
                        <font color="red">
                            ${deleteError}
                        </font>
                    </c:if>
                <c:if test="${empty list_search_name}">
                    <h2>No User found!!!</h2>
                </c:if>
            </c:if>
                    <form action="logoutButton">
                        <input type="submit" value="Logout" />
                    </form>
    </body>
</html>
