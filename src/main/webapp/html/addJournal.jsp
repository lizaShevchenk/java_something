<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Journal</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-dark text-light">

<!-- Page Header -->
<div class="container mt-4">
    <h1 class="text-center mb-4">Add New Journal</h1>

    <!-- Add Book Form -->
    <form action="/javaLearnApp/addJournal" method="post">
        <div class="form-group">
            <label for="name">Journal Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="countPages">Journal Count Pages:</label>
            <input type="number" class="form-control" id="countPages" name="countPages" required>
        </div>
        <div class="form-group">
            <label for="number">Journal Number:</label>
            <input type="number" class="form-control" id="number" name="number" required>
        </div>
        <div class="form-group">
            <label for="publicationYear">Journal Publication Year:</label>
            <input type="number" class="form-control" id="publicationYear" name="publicationYear" required>
        </div>

        <button type="submit" class="btn btn-dark">Add Journal</button>
    </form>

    <c:if test="${not empty sessionScope.errorMessage}">
        <div class="alert alert-danger mt-4" role="alert">
            ${sessionScope.errorMessage}
        </div>
        <!-- Remove the message after displaying it once -->
        <c:remove var="errorMessage" scope="session" />
    </c:if>

    <a href="/javaLearnApp/showJournals" class="btn btn-dark mt-3 d-block">Back to Journals</a>
    <a href="/javaLearnApp/index" class="btn btn-dark d-block">Back to Home</a>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
