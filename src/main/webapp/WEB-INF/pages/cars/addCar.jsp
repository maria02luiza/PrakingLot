<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 18.11.2025
  Time: 08:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTamplate pageTitle="Cars">
    <h1>Add Car</h1>
    <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/AddCar">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="license_plate">License Plate</label>
                <input type="text" class="form-control" id="license_plate" name="license_plate" placeholder="" value=""required>
                <div class="invalid-feedback">
                    License Plate is required.
                </div>
            </div>
        </div>
        <div class="col-md-6 mb-3">
            <label for="parking_spot" class="form-label">Parking Spot</label>
            <input type="text" class="form-control" id="parking_spot" name="parking_spot" required>
            <div class="invalid-feedback">
                Parking spot is required.
            </div>
        </div>
        <div class="col-md-6 mb-3">
            <label for="owner_id" class="form-label">Owner</label>
            <select class="form-select" id="owner_id" name="owner_id" required>
                <option value="">Choose...</option>
                <c:forEach var="user" items="${users}" varStatus="status">
                    <option value="${user.id}">${user.username}</option>
                </c:forEach>
            </select>
            <div class="invalid-feedback">
                Please select an owner.
            </div>
        </div>

        <button class="btn btn-primary mt-3" type="submit">Save</button>

    </form>
</t:pageTamplate>