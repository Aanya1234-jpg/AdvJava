import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/LibraryServlet"})
public class LibraryServlet extends HttpServlet {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String action = request.getParameter("action");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            if ("view".equals(action)) {
                String bookName = request.getParameter("BookName");

                if (bookName == null || bookName.isEmpty()) {
                    response.getWriter().println("Please provide a valid book name.");
                    return;
                }

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

                    response.getWriter().println("Book ID: " + bookId);
                    response.getWriter().println("Book Name: " + name);
                    response.getWriter().println("Author: " + author);
                    response.getWriter().println("Category: " + category);
                }

                if (!found) {
                    response.getWriter().println("No books found with the provided name.");
                }

            } else if ("delete".equals(action)) {
                String bookName = request.getParameter("bookname");

                if (bookName == null || bookName.isEmpty()) {
                    response.getWriter().println("Please provide a valid book name for deletion.");
                    return;
                }

                pst = conn.prepareStatement("DELETE FROM books WHERE BookName = ?");
                pst.setString(1, bookName);
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    response.getWriter().println("Book deleted successfully.");
                } else {
                    response.getWriter().println("No book found to delete.");
                }

            } else {
                response.getWriter().println("Invalid action specified.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred while processing your request: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            String bookId = request.getParameter("bookid");
            String bookName = request.getParameter("bookname");
            String author = request.getParameter("author");
            String category = request.getParameter("category");

            if (bookId == null || bookName == null || author == null || category == null ||
                bookId.isEmpty() || bookName.isEmpty() || author.isEmpty() || category.isEmpty()) {
                response.getWriter().println("Please provide all required fields.");
                return;
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            pst = conn.prepareStatement("INSERT INTO books (BookId, BookName, AuthorName, Category) VALUES (?, ?, ?, ?)");
            pst.setString(1, bookId);
            pst.setString(2, bookName);
            pst.setString(3, author);
            pst.setString(4, category);

            int rowsAffected = pst.executeUpdate();
            PrintWriter out = response.getWriter();
            if (rowsAffected > 0) {
                out.println("Book inserted successfully.");
            } else {
                out.println("Failed to insert book.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred while processing your request: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
