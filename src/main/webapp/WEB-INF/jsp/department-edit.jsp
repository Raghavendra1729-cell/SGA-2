<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Department</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="page-shell narrow">
    <section class="card">
        <div class="section-head">
            <h1>Edit Department</h1>
            <a class="link-button" href="${pageContext.request.contextPath}/">Back to Dashboard</a>
        </div>
        <form action="${pageContext.request.contextPath}/departments/${departmentForm.id}" method="post" class="stack-form">
            <label>Name</label>
            <input type="text" name="name" value="${departmentForm.name}">
            <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.departmentForm'].fieldErrors}">
                <c:if test="${error.field eq 'name'}">
                    <small class="error">${error.defaultMessage}</small>
                </c:if>
            </c:forEach>

            <label>Location</label>
            <input type="text" name="location" value="${departmentForm.location}">
            <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.departmentForm'].fieldErrors}">
                <c:if test="${error.field eq 'location'}">
                    <small class="error">${error.defaultMessage}</small>
                </c:if>
            </c:forEach>

            <label>Phone Extension</label>
            <input type="text" name="phoneExtension" value="${departmentForm.phoneExtension}">
            <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.departmentForm'].fieldErrors}">
                <c:if test="${error.field eq 'phoneExtension'}">
                    <small class="error">${error.defaultMessage}</small>
                </c:if>
            </c:forEach>

            <label>Established Year</label>
            <input type="number" name="establishedYear" value="${departmentForm.establishedYear}">
            <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.departmentForm'].fieldErrors}">
                <c:if test="${error.field eq 'establishedYear'}">
                    <small class="error">${error.defaultMessage}</small>
                </c:if>
            </c:forEach>

            <button type="submit">Update Department</button>
        </form>
    </section>
</div>
</body>
</html>
