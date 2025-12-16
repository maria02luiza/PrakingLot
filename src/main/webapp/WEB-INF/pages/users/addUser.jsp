<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTamplate pageTitle="Add User">
    <h1>Add User</h1>
    <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/AddUser">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Username is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Valid email is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="" value="" required>
                <div class="invalid-feedback">
                    Password is required.
                </div>
            </div>
        </div>

        <div class="col-md-6 mb-3">
            <label class="form-label">User Groups</label>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="user_groups" value="READ_CARS" id="read_cars">
                <label class="form-check-label" for="read_cars">READ_CARS</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="user_groups" value="WRITE_CARS" id="write_cars">
                <label class="form-check-label" for="write_cars">WRITE_CARS</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="user_groups" value="READ_USERS" id="read_users">
                <label class="form-check-label" for="read_users">READ_USERS</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="user_groups" value="WRITE_USERS" id="write_users">
                <label class="form-check-label" for="write_users">WRITE_USERS</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="user_groups" value="INVOICING" id="invoicing">
                <label class="form-check-label" for="invoicing">INVOICING</label>
            </div>
        </div>

        <button class="btn btn-primary mt-3" type="submit">Save</button>
    </form>
</t:pageTamplate>