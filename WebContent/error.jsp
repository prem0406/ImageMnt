<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<style>
    .container{
        display: flex;
        flex-direction: column;
        text-align: center;
    }
</style>
</head>
<body>

    <div class="container">
        <h3>Oops! ${alertMsg}</h3>
		<a href = "list">GO BACK</a>
    </div>

</body>
</html>