<!DOCTYPE html>
<html xmlns:ftl="http://www.freemarker.org/">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
        }
        form {
            max-width: 400px;
            margin: auto;
        }
        .buttons {
            display: flex;
            justify-content: space-between;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        a {
            color: #007BFF;
            text-decoration: none;
            font-size: 16px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>Login</h1>
<form action="${springMacroRequestContext.contextPath}/login" method="post">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div class="buttons">
        <button type="submit">Login</button>
        <a href="${springMacroRequestContext.contextPath}/member/join">Join</a>
    </div>
</form>
<a href="${springMacroRequestContext.contextPath}/oauth2/authorization/naver">Login with Naver</a>
</body>
</html>
