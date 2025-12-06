<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTamplate pageTitle="User">
    <h1>Users</h1>

    <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/AddUser" role="button">Add User</a>
        <button type="button" class="btn btn-secondary" onclick="document.getElementById('userForm').submit()">Invoice</button>
    </c:if>

    <div class="container text-center">
        <form id="userForm" method="POST" action="${pageContext.request.contextPath}/Users">
            <c:forEach var="u" items="${users}">
                <div class="row">
                    <div class="col">
                        <input class="form-check-input" type="checkbox" name="user_ids" value="${u.id}" id="user_${u.id}" />
                    </div>
                    <div class="col">${u.username}</div>
                    <div class="col">${u.email}</div>
                </div>
            </c:forEach>
        </form>

        <c:if test="${not empty invoices}">
            <h2>Invoices</h2>
            <c:forEach var="username" items="${invoices}" varStatus="status">
                ${status.index + 1}. ${username}
                <br/>
            </c:forEach>
        </c:if>
    </div>
</t:pageTamplate>