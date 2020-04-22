<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Authorization</title>
</head>
<body>
    <h2>Authorization</h2>
    <form action="IndexServlet" method="post">
        <label>Login</label>
        <input name="login"><br>
        <label>Password</label>
        <input name="password" type="password"><br>
        <input type="submit" value="Enter">
    </form>
</body>
</html>
