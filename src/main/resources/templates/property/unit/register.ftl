<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register Unit</title>
</head>
<body>
<h1>Register Unit</h1>
<form action="/property/unit/save" method="post">
    <input type="hidden" id="propertyId" name="propertyId" value="${property.id}" />
    <p>Property: ${property.address}</p>
    <label for="unitNumber">Unit Number:</label>
    <input type="text" id="unitNumber" name="unitNumber" required>
    <button type="submit">Save Unit</button>
</form>
</body>
</html>