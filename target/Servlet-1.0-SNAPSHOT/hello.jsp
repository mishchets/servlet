<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Page</title>
</head>
<body>
    <p>Hello, ${login}</p>
    <p>Password: ${cookie.get("password").value}</p>
    <a href="image">Get image</a>
    <form action="Hello" method="post">
        <input type="submit" value="Exit">
    </form>
</body>
</html>
