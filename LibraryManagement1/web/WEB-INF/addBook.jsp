<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Book</title>
</head>
<body>
    <h2>Add a New Book</h2>
    <form action="UserInteraction" method="post">
        <label for="bookid">Book ID:</label><br>
        <input type="text" id="bookid" name="bookid" required><br><br>

        <label for="bookname">Book Name:</label><br>
        <input type="text" id="bookname" name="bookname" required><br><br>

        <label for="author">Author:</label><br>
        <input type="text" id="author" name="author" required><br><br>

        <label for="category">Category:</label><br>
        <input type="text" id="category" name="category" required><br><br>

        <input type="submit" value="Add Book">
    </form>
    <br>
    <a href="HomePage.jsp">Back to Home</a>
</body>
</html>
