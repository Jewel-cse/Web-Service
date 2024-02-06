<html>

<head>
<title> Login Page</title>
</head>
<body>
<%@ include file = "common/navigation.jspf"%>

<div>
    <h1>WELCOME TO THE LOGIN PAGE !!</h1>
    <pre>${errorMessage}</pre>
    <form method="post">
        <label>
            Name :
            <input type="text" name="name">
        </label><br>
        <label>
            Password:
            <input type="password" name="password">
        </label><br>
        <input type = "submit">
    </form>
</div>
</body>

</html>
