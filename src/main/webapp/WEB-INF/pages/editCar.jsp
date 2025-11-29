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

<t:pageTamplate pageTitle="Edit Car">
    <h1>Edit Car</h1>
    <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/EditCar">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="license_plate">License Plate</label>
                <input type="text" class="form-control" id="license_plate" name="license_plate" placeholder="" value="${licensePlate}"required>
                <div class="invalid-feedback">
                    License Plate is required.
                </div>
            </div>
        </div>
        <div class="col-md-6 mb-3">
            <label for="parking_spot" class="form-label">Parking Spot</label>
            <input type="text" class="form-control" id="parking_spot" name="parking_spot"  placeholder="" value="${parlingSpot}" required>
            <div class="invalid-feedback">
                Parking spot is required.
            </div>
        </div>
        <div class="col-md-6 mb-3">
            <label for="owner_id" class="form-label">Owner</label>
            <select class="form-select" id="owner_id" name="owner_id" required>
                <option value="">Choose...</option>
                <c:forEach var="user" items="${users}" varStatus="status">
                    <option value="${user.id}"${ownerName eq user.username?'selected':''}>${username}</option>
                </c:forEach>
            </select>
            <div class="invalid-feedback">
            </div>
        </div>
        <hr class="mb-4">
        <input type="hidden" name="car_id" value="${car.id}">
        <button class="btn btn-primary mt-3" type="submit">Save Changes</button>
        <a href="${pageContext.request.contextPath}/Cars" class="btn btn-secondary mt-3">Cancel</a>

    </form>
</t:pageTamplate>