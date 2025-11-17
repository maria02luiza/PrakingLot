<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 17.11.2025
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTamplate pageTitle="Cars">
<h1>Users</h1>

<div class="container text-center">
    <c:forEach var="u" items="${users}">
        <div class="row">
            <div class="col">${u.username}</div>
            <div class="col">${u.email}</div>
        </div>
    </c:forEach>
</div>
</t:pageTamplate>
