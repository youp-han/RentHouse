<!DOCTYPE html>
<html xmlns:ftl="http://www.freemarker.org/">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="${springMacroRequestContext.contextPath}/login" method="post">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username">
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
    </div>
    <div>
        <button type="submit">Login</button>
    </div>
</form>
<a href="${springMacroRequestContext.contextPath}/oauth2/authorization/naver">Login with Naver</a>
</body>
</html>
