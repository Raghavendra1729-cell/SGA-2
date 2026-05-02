<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Department and Employee Manager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="page-shell">
    <header class="hero">
        <div>
            <p class="eyebrow">Spring Boot CRUD Dashboard</p>
            <h1>Department and Employee Manager</h1>
            <p class="hero-copy">Create, view, and update records while also exploring a custom inner join report between both entities.</p>
        </div>
        <div class="hero-badge">
            <span>10 departments</span>
            <span>10 employees</span>
            <span>H2 + JSP + JPA</span>
        </div>
    </header>

    <c:if test="${not empty successMessage}">
        <div class="alert success">${successMessage}</div>
    </c:if>

    <main class="grid-two">
        <section class="card">
            <h2>Add Department</h2>
            <form action="${pageContext.request.contextPath}/departments" method="post" class="stack-form">
                <label>Name</label>
                <input type="text" name="name" value="${departmentForm.name}" placeholder="Engineering">
                <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.departmentForm'].fieldErrors}">
                    <c:if test="${error.field eq 'name'}">
                        <small class="error">${error.defaultMessage}</small>
                    </c:if>
                </c:forEach>

                <label>Location</label>
                <input type="text" name="location" value="${departmentForm.location}" placeholder="Bengaluru">
                <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.departmentForm'].fieldErrors}">
                    <c:if test="${error.field eq 'location'}">
                        <small class="error">${error.defaultMessage}</small>
                    </c:if>
                </c:forEach>

                <label>Phone Extension</label>
                <input type="text" name="phoneExtension" value="${departmentForm.phoneExtension}" placeholder="ENG-101">
                <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.departmentForm'].fieldErrors}">
                    <c:if test="${error.field eq 'phoneExtension'}">
                        <small class="error">${error.defaultMessage}</small>
                    </c:if>
                </c:forEach>

                <label>Established Year</label>
                <input type="number" name="establishedYear" value="${departmentForm.establishedYear}" placeholder="2010">
                <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.departmentForm'].fieldErrors}">
                    <c:if test="${error.field eq 'establishedYear'}">
                        <small class="error">${error.defaultMessage}</small>
                    </c:if>
                </c:forEach>

                <button type="submit">Create Department</button>
            </form>
        </section>

        <section class="card">
            <h2>Add Employee</h2>
            <form action="${pageContext.request.contextPath}/employees" method="post" class="stack-form">
                <label>Full Name</label>
                <input type="text" name="fullName" value="${employeeForm.fullName}" placeholder="Aarav Sharma">
                <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.employeeForm'].fieldErrors}">
                    <c:if test="${error.field eq 'fullName'}">
                        <small class="error">${error.defaultMessage}</small>
                    </c:if>
                </c:forEach>

                <label>Email</label>
                <input type="email" name="email" value="${employeeForm.email}" placeholder="name@company.com">
                <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.employeeForm'].fieldErrors}">
                    <c:if test="${error.field eq 'email'}">
                        <small class="error">${error.defaultMessage}</small>
                    </c:if>
                </c:forEach>

                <label>Job Title</label>
                <input type="text" name="jobTitle" value="${employeeForm.jobTitle}" placeholder="Software Engineer">
                <c:forEach var="error" items="${requestScope['org.springframework.validation.BindingResult.employeeForm'].fieldErrors}">
                    <c:if test="${error.field eq 'jobTitle'}">
                        <small class="error">${error.defaultMessage}</small>
                    </c:if>
                </c:forEach>

                <label>Salary</label>
                <input type="number" step="0.01" name="salary" value="${employeeForm.salary}" placeholder="65000">
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

                <button type="submit">Create Employee</button>
            </form>
        </section>
    </main>

    <section class="card">
        <div class="section-head">
            <h2>Departments</h2>
            <span>${departments.size()} total</span>
        </div>
        <div class="table-wrap">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Location</th>
                    <th>Phone Extension</th>
                    <th>Established</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="department" items="${departments}">
                    <tr>
                        <td>${department.id}</td>
                        <td>${department.name}</td>
                        <td>${department.location}</td>
                        <td>${department.phoneExtension}</td>
                        <td>${department.establishedYear}</td>
                        <td><a class="link-button" href="${pageContext.request.contextPath}/departments/${department.id}/edit">Edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <section class="card">
        <div class="section-head">
            <h2>Employees</h2>
            <span>${employees.size()} total</span>
        </div>
        <div class="table-wrap">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Job Title</th>
                    <th>Salary</th>
                    <th>Department</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.fullName}</td>
                        <td>${employee.email}</td>
                        <td>${employee.jobTitle}</td>
                        <td><fmt:formatNumber value="${employee.salary}" type="currency"/></td>
                        <td>${employee.department.name}</td>
                        <td><a class="link-button" href="${pageContext.request.contextPath}/employees/${employee.id}/edit">Edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <section class="card">
        <div class="section-head">
            <h2>Employee-Department Inner Join Report</h2>
            <span>Custom repository query</span>
        </div>
        <div class="table-wrap">
            <table>
                <thead>
                <tr>
                    <th>Employee</th>
                    <th>Email</th>
                    <th>Job Title</th>
                    <th>Department</th>
                    <th>Location</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${employeeDepartmentViews}">
                    <tr>
                        <td>${item.employeeName}</td>
                        <td>${item.email}</td>
                        <td>${item.jobTitle}</td>
                        <td>${item.departmentName}</td>
                        <td>${item.departmentLocation}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</div>
</body>
</html>
