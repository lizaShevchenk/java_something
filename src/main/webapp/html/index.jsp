<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My library</title>

    <!-- Bootstrap CSS (with custom theme from Bootswatch) -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/cosmo/bootstrap.min.css">

    <!-- jQuery (required for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>

    <!-- Popper.js (required for Bootstrap's dropdowns and tooltips) -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.0/dist/umd/popper.min.js"></script>

    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="/javaLearnApp/index">My library</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/javaLearnApp/showBooks">Show books</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/javaLearnApp/addBook">Add book</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/javaLearnApp/showAuthors">Show authors</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/javaLearnApp/addAuthor">Add author</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/javaLearnApp/showJournals">Show journals</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/javaLearnApp/addJournal">Add journal</a>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- Success Message -->
        <c:if test="${not empty sessionScope.message}">
            <div class="alert alert-success mt-4" role="alert">
                ${sessionScope.message}
            </div>
            <!-- Remove the message after displaying it once -->
            <c:remove var="message" scope="session" />
        </c:if>

    </body>
</html>