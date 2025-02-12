<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Journal List</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- Optional: Bootstrap Theme (For styling) -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap-theme.min.css" rel="stylesheet">

</head>
<body>

<div class="container mt-4">
    <h1 class="text-center mb-4">Journal in the Library</h1>

    <c:if test="${not empty journals}">
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>Journal Name</th>
                    <th>Count Pages</th>
                    <th>Number</th>
                    <th>Publication Year</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="journal" items="${journals}">
                    <tr>
                        <td>${journal.name}</td>
                        <td>${journal.countPages}</td>
                        <td>${journal.number}</td>
                        <td>${journal.publicationYear}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty journals}">
        <div class="alert alert-warning" role="alert">
            No journals found in the library.
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.message}">
        <div class="alert alert-danger mt-4" role="alert">
            ${sessionScope.message}
        </div>
        <!-- Remove the message after displaying it once -->
        <c:remove var="message" scope="session" />
    </c:if>
    <a href="/javaLearnApp/addJournal" class="btn btn-dark mb-4 d-block">Add New Journal</a>
    <a href="/javaLearnApp/index" class="btn btn-dark d-block">Back to Home</a>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
