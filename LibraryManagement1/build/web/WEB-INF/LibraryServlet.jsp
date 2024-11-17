<%-- 
    Document   : LibraryServlet
    Created on : 17 Nov, 2024, 8:46:06 PM
    Author     : user
--%>
<%@ page import="java.sql.*, javax.servlet.*, javax.servlet.http.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Actions Result</title>
</head>
<body>
    <h2>Library Actions Result</h2>

    <%
        String action = request.getParameter("action");
        if ("view".equals(action)) {
            String bookName = request.getParameter("BookName");
            Connection conn = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
                pst = conn.prepareStatement("SELECT * FROM books WHERE BookName = ?");
                pst.setString(1, bookName);
                rs = pst.executeQuery();

                boolean found = false;
                while (rs.next()) {
                    found = true;
                    String bookId = rs.getString("BookId");
                    String name = rs.getString("BookName");
                    String author = rs.getString("AuthorName");
                    String category = rs.getString("Category");

                    out.println("<p>Book ID: " + bookId + "</p>");
                    out.println("<p>Book Name: " + name + "</p>");
                    out.println("<p>Author: " + author + "</p>");
                    out.println("<p>Category: " + category + "</p>");
                }

                if (!found) {
                    out.println("<p>No books found with the name: " + bookName + "</p>");
                }

            } catch (Exception e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pst != null) pst.close();
                    if (conn != null) conn.close();
                } catch (Exception e) {
                    out.println("<p>Error closing resources: " + e.getMessage() + "</p>");
                }
            }
        } else if ("delete".equals(action)) {
            String bookName = request.getParameter("bookname");

            Connection conn = null;
            PreparedStatement pst = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
                pst = conn.prepareStatement("DELETE FROM books WHERE BookName = ?");
                pst.setString(1, bookName);
                int rows = pst.executeUpdate();

                if (rows > 0) {
                    out.println("<p>Book deleted successfully: " + bookName + "</p>");
                } else {
                    out.println("<p>No book found with the name: " + bookName + "</p>");
                }

            } catch (Exception e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            } finally {
                try {
                    if (pst != null) pst.close();
                    if (conn != null) conn.close();
                } catch (Exception e) {
                    out.println("<p>Error closing resources: " + e.getMessage() + "</p>");
                }
            }
        } else {
            out.println("<p>Invalid action specified.</p>");
        }
    %>
    <br>
    <a href="HomePage.jsp">Back to Home</a>
</body>
</html>
