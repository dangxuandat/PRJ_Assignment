<%-- 
    Document   : CreateNewAccountError
    Created on : Jul 6, 2021, 3:09:31 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <c:set var="errors" value="${requestScope.errors}"/>
        <h1>CREATE NEW ACCOUNT</h1>
        <form action="createAccountButton">
            <c:if test="${not empty errors.usernameLengthError}">
                <font color = "red">
                    ${errors.usernameLengthError}
                    </font><br>
            </c:if>
                
            <c:if test="${not empty errors.duplicatedUsername}">
                <font color = "red">
                    ${errors.duplicatedUsername}
                    </font><br>
            </c:if>
                    Username: <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 to 20 characters)<br>
            
            <c:if test="${not empty errors.passwordLengthError}">
                <font color = "red">
                    ${errors.passwordLengthError}
                    </font><br>
            </c:if>
                Password: <input type="password" name="txtPassword" value="" />(6 to 30 characters)<br>
            
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color = "red">
                    ${errors.confirmNotMatch}
                    </font><br>
            </c:if>
                Confirm Password: <input type="password" name="txtConfirmPassword" value="" /><br>
            
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color = "red">
                    ${errors.fullnameLengthError}
                    </font><br>
            </c:if>
                    Full Name: <input type="text" name="txtFullName" value="${param.txtFullName}" />(2 to 50 characters)<br>
            <input type="submit" value="Create Account" />
        </form>
    </body>
</html>
