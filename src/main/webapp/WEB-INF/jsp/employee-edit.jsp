<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Employee</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="page-shell narrow">
    <section class="card">
        <div class="section-head">
            <h1>Edit Employee</h1>
            <a class="link-button" href="${pageContext.request.contextPath}/">Back to Dashboard</a>
        </div>
        <form action="${pageContext.request.contextPath}/employees/${employeeForm.id}" method="post" class="stack-form">
            <label>Full Name</label>
            <input type="text" name="fullName" value="${employeeForm.fullName}">
            <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.employeeForm'].fieldErrors}">
                <c:if test="${error.field eq 'fullName'}">
                    <small class="error">${error.defaultMessage}</small>
                </c:if>
            </c:forEach>

            <label>Email</label>
            <input type="email" name="email" value="${employeeForm.email}">
            <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.employeeForm'].fieldErrors}">
                <c:if test="${error.field eq 'email'}">
                    <small class="error">${error.defaultMessage}</small>
                </c:if>
            </c:forEach>

            <label>Job Title</label>
            <input type="text" name="jobTitle" value="${employeeForm.jobTitle}">
            <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.employeeForm'].fieldErrors}">
                <c:if test="${error.field eq 'jobTitle'}">
                    <small class="error">${error.defaultMessage}</small>
                </c:if>
            </c:forEach>

            <label>Salary</label>
            <input type="number" step="0.01" name="salary" value="${employeeForm.salary}">
            <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.employeeForm'].fieldErrors}">
                <c:if test="${error.field eq 'salary'}">
                    <small class="error">${error.defaultMessage}</small>
                </c:if>
            </c:forEach>

            <label>Department</label>
            <select name="departmentId">
                <option value="">Select Department</option>
                <c:forEach var="department" items="${departments}">
                    <option value="${department.id}" <c:if test="${employeeForm.departmentId eq department.id}">selected</c:if>>${department.name}</option>
                </c:forEach>
            </select>
            <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.employeeForm'].fieldErrors}">
                <c:if test="${error.field eq 'departmentId'}">
                    <small class="error">${error.defaultMessage}</small>
                </c:if>
            </c:forEach>

            <button type="submit">Update Employee</button>
        </form>
    </section>
</div>
</body>
</html>
