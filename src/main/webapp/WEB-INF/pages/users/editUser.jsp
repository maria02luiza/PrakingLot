<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTamplate pageTitle="Edit User">
    <h1>Edit User</h1>
    <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/EditUser">
        <input type="hidden" name="user_id" value="${user.id}" />

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" value="${user.username}" required>
                <div class="invalid-feedback">
                    Username is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
                <div class="invalid-feedback">
                    Valid email is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Leave empty to keep current password">
                <div class="form-text">Leave empty if you don't want to change the password.</div>
            </div>
        </div>

        <div class="col-md-6 mb-3">
            <label class="form-label">User Groups</label>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="user_groups" value="READ_CARS" id="read_cars"
                    ${userGroups.contains('READ_CARS') ? 'checked' : ''}>
                <label class="form-check-label" for="read_cars">READ_CARS</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="user_groups" value="WRITE_CARS" id="write_cars"
                    ${userGroups.contains('WRITE_CARS') ? 'checked' : ''}>
                <label class="form-check-label" for="write_cars">WRITE_CARS</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="user_groups" value="READ_USERS" id="read_users"
                    ${userGroups.contains('READ_USERS') ? 'checked' : ''}>
                <label class="form-check-label" for="read_users">READ_USERS</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="user_groups" value="WRITE_USERS" id="write_users"
                    ${userGroups.contains('WRITE_USERS') ? 'checked' : ''}>
                <label class="form-check-label" for="write_users">WRITE_USERS</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="user_groups" value="INVOICING" id="invoicing"
                    ${userGroups.contains('INVOICING') ? 'checked' : ''}>
                <label class="form-check-label" for="invoicing">INVOICING</label>
            </div>
        </div>

        <button class="btn btn-primary mt-3" type="submit">Save</button>
        <a href="${pageContext.request.contextPath}/Users" class="btn btn-secondary mt-3">Cancel</a>
    </form>

    <script src="${pageContext.request.contextPath}/scripts/form-validation.js"></script>
</t:pageTamplate>