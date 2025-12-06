<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTamplate pageTitle="Add Car Photo">
    <h1>Add Photo for Car: ${car.licensePlate}</h1>

    <form method="POST" action="${pageContext.request.contextPath}/AddCarPhoto" enctype="multipart/form-data">
        <input type="hidden" name="car_id" value="${car.id}" />

        <div class="mb-3">
            <label for="file" class="form-label">Select Photo</label>
            <input type="file" class="form-control" id="file" name="file" accept="image/*" required>
        </div>

        <button type="submit" class="btn btn-primary">Upload Photo</button>
        <a href="${pageContext.request.contextPath}/Cars" class="btn btn-secondary">Cancel</a>
    </form>
</t:pageTamplate>