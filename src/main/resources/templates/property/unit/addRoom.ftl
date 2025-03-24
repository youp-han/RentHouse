<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
        }
        form {
            max-width: 500px;
            margin: auto;
        }
        label {
            font-weight: bold;
        }
        input, select, button {
            width: 100%;
            margin-top: 5px;
            margin-bottom: 15px;
            padding: 10px;
            font-size: 1rem;
        }
        button {
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h1>Room Registration</h1>

<!-- Error message display -->
<#if errorMessage??>
    <p class="error">${errorMessage}</p>
</#if>

<form action="/admin/property/unit/unitAttribute/save" method="POST">
    <!-- unitId 전달 -->
    <#if unitId??>
        <input type="hidden" name="unitId" value="${unitId?replace(',', '')}">
        <!-- Room Size -->
        <label for="size">Room Size (in square meters):</label>
        <input type="number" step="0.01" id="size" name="size" value="29.7" required>
        <br>

        <!-- Room Features -->
        <label for="features">Room Features:</label>
        <input type="text" id="features" name="features" value="화장실 1, 부엌 1" required>
        <br>

        <!-- Room Type -->
        <label for="type">Room Type:</label>
        <select id="type" name="type" required>
            <option value="" disabled selected>Select a unitAttribute type</option>
            <option value="oneRoom">One Room</option>
            <option value="twoRooms">Two Rooms</option>
            <option value="threeRooms">Three Rooms</option>
        </select>
        <br>

        <!-- Submit Button -->
        <button type="submit">Register Room</button>

    <#else>
        <p class="error">Error: Unit ID is not available!</p>
    </#if>
</form>
</body>
</html>
