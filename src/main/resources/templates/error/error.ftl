<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
        }
        h1 {
            font-size: 2rem;
        }
        p {
            font-size: 1rem;
        }
        a {
            color: #0056b3;
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>오류 발생!</h1>
<#if statusCode??>
    <p>상태 코드: ${statusCode}</p>
</#if>
<#if errorMessage??>
    <p>오류 메시지: ${errorMessage}</p>
</#if>
<#if exception??>
    <p>예외: ${exception}</p>
</#if>
<p>죄송합니다. 문제가 발생했습니다. 잠시 후 다시 시도하거나 문제가 계속되면 지원팀에 문의하십시오.</p>
<a href="/">Go back to Home</a>
</body>

</html>

