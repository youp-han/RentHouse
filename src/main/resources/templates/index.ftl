<!DOCTYPE html>
<html xmlns:ftl="http://www.freemarker.org/">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h1>Welcome to Rent-House</h1>
<div>
    <#if name??>
        <p>Welcome, ${name}!</p>
        <a href="${springMacroRequestContext.contextPath}/logout" onclick="event.preventDefault(); document.getElementById('logout-form').submit();">Logout</a>
    <#else>
        <a href="${springMacroRequestContext.contextPath}/login">Login</a>
        <a href="${springMacroRequestContext.contextPath}/member/join">Join</a>
    </#if>
</div>
<form id="logout-form" action="${springMacroRequestContext.contextPath}/logout" method="post" style="display: none;">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>