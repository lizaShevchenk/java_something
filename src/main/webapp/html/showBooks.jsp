<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book List</title>

    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- Optional: Bootstrap Theme (For styling) -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap-theme.min.css" rel="stylesheet">

</head>
<body>

<div class="container mt-4">
    <h1 class="text-center mb-4">Books in the Library</h1>

    <c:if test="${not empty books}">
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>Book Name</th>
                    <th>Number of Pages</th>
                    <th>Author ID</th>
                </tr>
            </thead>
            <tbody>
                <!-- Loop through the books and display them in table rows -->
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book.name}</td>
                        <td>${book.countPages}</td>
                        <td>${book.authorId}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty books}">
        <div class="alert alert-warning" role="alert">
            No books found in the library.
        </div>
    </c:if>
    <a href="/javaLearnApp/addBook" class="btn btn-dark mb-4 d-block">Add New Book</a>
    <a href="/javaLearnApp/index" class="btn btn-dark d-block">Back to Home</a>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
