<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTamplate pageTitle="User">
    <h1>Users</h1>

    <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/AddUser" role="button">Add User</a>
    </c:if>

    <%-- Afișează butonul Invoice DOAR dacă utilizatorul are rolul INVOICING --%>
    <c:if test="${pageContext.request.isUserInRole('INVOICING')}">
        <button type="button" class="btn btn-secondary" onclick="document.getElementById('userForm').submit()">Invoice</button>
    </c:if>

    <div class="container text-center">
        <form id="userForm" method="POST" action="${pageContext.request.contextPath}/Users">
            <c:forEach var="u" items="${users}">
                <div class="row mb-2">
                        <%-- Afișează checkbox DOAR dacă utilizatorul are rolul INVOICING --%>
                    <c:if test="${pageContext.request.isUserInRole('INVOICING')}">
                        <div class="col">
                            <input class="form-check-input" type="checkbox" name="user_ids" value="${u.id}" id="user_${u.id}" />
                        </div>
                    </c:if>
                    <div class="col">${u.username}</div>
                    <div class="col">${u.email}</div>

                        <%-- Afișează butonul Edit DOAR dacă utilizatorul are rolul WRITE_USERS --%>
                    <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
                        <div class="col">
                            <a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/EditUser?id=${u.id}">Edit</a>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </form>

            <%-- Afișează secțiunea Invoices DOAR dacă utilizatorul are rolul INVOICING --%>
        <c:if test="${pageContext.request.isUserInRole('INVOICING') && not empty invoices}">
            <h2>Invoices</h2>
            <c:forEach var="username" items="${invoices}" varStatus="status">
                ${status.index + 1}. ${username}
                <br/>
            </c:forEach>
        </c:if>
    </div>
</t:pageTamplate>